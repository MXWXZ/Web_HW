import React, { Component } from 'react';
import { Tag, Row, Table } from 'antd';
import axios from 'axios';

const columns = [
    {
        title: 'Order ID',
        dataIndex: 'orderId',
        align: 'center',
        sorter: (a, b) => a.orderId - b.orderId,
    }, {
        title: 'Username',
        dataIndex: 'userName',
        align: 'center',
        sorter: (a, b) => a.userName.localeCompare(b.userName),
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
    }
];

class OrderManage extends Component {
    state = {
        order: [],
        orderSave: []
    }

    constructor(props) {
        super(props);

        axios.get(`/api/orders`)
            .then(res => {
                this.setState({
                    order: res.data.data,
                    orderSave: res.data.data
                })
            });
    }

    render() {
        return (
            <Row>
                <Table rowKey={record => record.orderId} bordered={true} columns={columns} dataSource={this.state.order} />
            </Row>
        );
    }
}

export default OrderManage;
