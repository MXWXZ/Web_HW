import React, { Component } from 'react';
import { Tag, Button, Row, Table } from 'antd';
import axios from 'axios';

const columns = [
    {
        title: 'ID',
        dataIndex: 'userId',
        align: 'center',
        sorter: (a, b) => a.userId - b.userId,
    }, {
        title: 'Username',
        dataIndex: 'userName',
        align: 'center',
        sorter: (a, b) => a.userName.localeCompare(b.userName),
    }, {
        title: 'Email',
        dataIndex: 'userEmail',
        align: 'center',
        sorter: (a, b) => a.userEmail.localeCompare(b.userEmail),
    }, {
        title: 'Permission',
        dataIndex: 'userPermission',
        align: 'center',
        sorter: (a, b) => a.userPermission - b.userPermission,
        render: userPermission => {
            if (userPermission === 1)
                return <Tag color='red'>admin</Tag>
            else
                return <Tag color='green'>user</Tag>
        }
    }, {
        title: 'Status',
        dataIndex: 'userStatus',
        align: 'center',
        sorter: (a, b) => a.userStatus - b.userStatus,
        render: userStatus => {
            if (userStatus === 1)
                return <Tag color='orange'>frozen</Tag>
            else
                return <Tag color='green'>normal</Tag>
        }
    }, {
        title: 'Action',
        align: 'center',
        render: (text, record) => {
            if (record.userStatus === 1)
                return <Button size='small'>Unfreeze</Button>
            else
                return <Button size='small' type='danger'>Freeze</Button>
        }
    }
];

class UserManage extends Component {
    state = {
        user: [],
        userSave: []
    }

    constructor(props) {
        super(props);

        axios.get(`/api/users`)
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

export default UserManage;
