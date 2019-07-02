import React, { Component } from 'react';
import { Tag, Row, Table, Input } from 'antd';
import axios from 'axios';

const Search = Input.Search;

/*eslint-disable no-extend-native*/
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

class OrderView extends Component {
    state = {
        order: [],
        orderSave: [],
        subList: [],

        userId: sessionStorage.getItem('userId'),
        userPermission: sessionStorage.getItem('userPermission'),
    }

    constructor(props) {
        super(props);

        this.onExpand = this.onExpand.bind(this);
        this.findSublist = this.findSublist.bind(this);
        this.getSublist = this.getSublist.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        let url = `/api/orders`;
        if (this.state.userPermission === '0')
            url += '?userId=' + this.state.userId;

        axios.get(url, { headers: { token: sessionStorage.getItem('token') } })
            .then(res => {
                this.setState({
                    order: res.data.data,
                    orderSave: res.data.data
                })
            });
    }

    findSublist(key) {
        for (var i = 0; i < this.state.subList.length; ++i)
            if (this.state.subList[i].orderId === key)
                return i;
        return -1;
    }

    getSublist(key) {
        let index = this.findSublist(key);
        return index === -1 ? [] : this.state.subList[index];
    }

    onExpand(expanded, record) {
        if (expanded && this.findSublist(record.orderId) === -1) {
            axios.get(`/api/orders`, {
                params: {
                    userId: sessionStorage.getItem('userId'),
                    orderId: record.orderId
                },
                headers: {
                    token: sessionStorage.getItem('token')
                }
            })
                .then(res => {
                    res.data.data.orderId = record.orderId;
                    let newList = this.state.subList;
                    newList.push(res.data.data);
                    this.setState({
                        subList: newList
                    })
                });
        }
    }

    handleChange() {
        let pattern = document.getElementById('search').value;
        let list = this.state.orderSave.filter((item) => {
            return (item.orderId).toString().indexOf(pattern) !== -1 ||
                item.userName.indexOf(pattern) !== -1 ||
                (item.orderPrice).toString().indexOf(pattern) !== -1;
        })
        this.setState({
            order: list
        })
    }

    render() {
        const columns = [
            {
                title: 'Order ID',
                dataIndex: 'orderId',
                align: 'center',
                sorter: (a, b) => a.orderId - b.orderId,
            }, {
                title: 'Status',
                dataIndex: 'orderStatus',
                align: 'center',
                sorter: (a, b) => a.orderStatus - b.orderStatus,
                render: orderStatus => {
                    let color = '';
                    let text = '';
                    switch (orderStatus) {
                        case 0:
                            color = 'orange';
                            text = 'Nopay';
                            break;
                        case 1:
                            color = 'gold';
                            text = 'Payed';
                            break;
                        case 2:
                            color = 'blue';
                            text = 'Delivering';
                            break;
                        case 3:
                            color = 'purple';
                            text = 'Reached';
                            break;
                        case 4:
                            color = 'green';
                            text = 'Accepted';
                            break;
                        case 5:
                            color = 'red';
                            text = 'Canceled';
                            break;
                        default:
                            color = null;
                            text = 'Unknown';
                            break;
                    }
                    return <Tag color={color}>{text}</Tag>
                }
            }, {
                title: 'Time',
                dataIndex: 'orderTime',
                align: 'center',
                sorter: (a, b) => a.orderId - b.orderId,
                render: orderTime => ((new Date(parseInt(orderTime) * 1000)).Format('yyyy-MM-dd hh:mm:ss'))
            }, {
                title: 'Total',
                dataIndex: 'orderPrice',
                render: orderPrice => ((orderPrice / 100).toFixed(2))
            },
        ];

        const expandedRowRender = record => {
            const columns = [
                {
                    title: 'Name',
                    dataIndex: 'bookName'
                },
                {
                    title: 'Price',
                    dataIndex: 'bookPrice',
                    render: bookPrice => ((bookPrice / 100).toFixed(2))
                },
                {
                    title: 'Amount',
                    dataIndex: 'orderAmount'
                },
                {
                    title: 'Total',
                    render: (text, record) => ((record.bookPrice / 100 * record.orderAmount).toFixed(2))
                },
            ];

            return (
                <Table rowKey={record => record.orderItemId} columns={columns} dataSource={this.getSublist(record.orderId)} pagination={false} />
            );
        };

        if (this.state.userPermission === '1') {
            columns.splice(1, 0, {
                title: 'Username',
                dataIndex: 'userName',
                align: 'center',
                sorter: (a, b) => a.userName.localeCompare(b.userName),
            });
        }
        return (
            <div>
                <Row style={{ paddingBottom: '20px' }}>
                    <Search id='search' placeholder='search order' onChange={this.handleChange} style={{ width: 300 }} />
                </Row>
                <Row>
                    <Table rowKey={(record, index) => { record.key = index; return index }} expandRowByClick={true} expandedRowRender={expandedRowRender} bordered={true}
                        columns={columns} dataSource={this.state.order} onExpand={this.onExpand} />
                </Row>
            </div>
        );
    }
}

export default OrderView;
