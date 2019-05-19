import React, { Component } from 'react';
import { Tag, Row, Table } from 'antd';
import axios from 'axios';
import Cookies from 'universal-cookie';

const cookies = new Cookies();

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

        userId: cookies.get('userId'),
        userPermission: cookies.get('userPermission'),
    }

    constructor(props) {
        super(props);

        this.onExpand = this.onExpand.bind(this);
    }

    componentDidMount() {
        let url = `/api/orders`;
        if (this.state.userPermission === '0')
            url += '?userId=' + this.state.userId;

        axios.get(url)
            .then(res => {
                for (let i = 0; i < res.data.data.length; ++i)
                    this.state.subList.push([]);
                this.setState({
                    order: res.data.data,
                    orderSave: res.data.data
                })
            });
    }

    onExpand(expanded, record) {
        if (expanded && this.state.subList[record.key].length === 0) {
            axios.get(`/api/orders`, {
                params: {
                    orderId: record.orderId
                }
            })
                .then(res => {
                    let newList = this.state.subList;
                    newList[record.key] = res.data.data;
                    this.setState({
                        subList: newList
                    })
                });
        }
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
                <Table rowKey={record => record.orderItemId} columns={columns} dataSource={this.state.subList[record.key]} pagination={false} />
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
            <Row>
                <Table rowKey={(record, index) => { record.key = index; return index }} expandRowByClick={true} expandedRowRender={expandedRowRender} bordered={true}
                    columns={columns} dataSource={this.state.order} onExpand={this.onExpand} />
            </Row>
        );
    }
}

export default OrderView;
