import React, { Component } from 'react';
import { Tag, Button, Row, Table } from 'antd';
import axios from 'axios';

const columns = [
    {
        title: 'ID',
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
                    break;
            }
            return <Tag color={color}>{text}</Tag>
        }
    }, {
        title: 'Action',
        align: 'center',
        render: (text, record) => (
            <span>
                <Button size='small' type={record.userStatus === 1 ? null : 'danger'}>{record.userStatus === 1 ? 'Unfreeze' : 'Freeze'}</Button>
            </span>
        )
    }
];

class OrderManage extends Component {
    state = {
        order: [],
        orderSave: []
    }

    constructor(props) {
        super(props);

        axios.get(`/api/allusers`)
            .then(res => {
                this.setState({
                    user: res.data.data,
                    userSave: res.data.data
                })
            });
    }

    render() {
        return (
            <Row>
                <Table rowKey={record => record.userId} bordered={true} columns={columns} dataSource={this.state.user} />
            </Row>
        );
    }
}

export default OrderManage;
