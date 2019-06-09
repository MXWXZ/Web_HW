import React, { Component } from 'react';
import { Tag, Button, Row, Table, message } from 'antd';
import axios from 'axios';
import Qs from 'qs';

class UserManage extends Component {
    state = {
        user: [],
    }

    constructor(props) {
        super(props);

        this.freezeUser = this.freezeUser.bind(this);
    }

    componentDidMount() {
        axios.get(`/api/users`)
            .then(res => {
                this.setState({
                    user: res.data.data,
                })
            });
    }

    freezeUser = (e) => {
        let id = parseInt(e.target.id);
        let status = e.target.name === '0' ? 1 : 0
        axios.put(`/api/users`, Qs.stringify({
            userId: id,
            userStatus: status,
        }))
            .then(res => {
                if (res.data.code !== 0) {
                    message.error(res.data.msg);
                } else {
                    message.success('User permission updated!');
                    let newuser = this.state.user;
                    for (let i = 0, len = newuser.length; i < len; ++i) {
                        if (newuser[i].userId === id) {
                            newuser[i].userStatus = status;
                            break;
                        }
                    }
                    this.setState({
                        user: newuser,
                    })
                }
            });
    }

    render() {
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
                        return <Button id={record.userId} name={record.userStatus} size='small' onClick={this.freezeUser}>Unfreeze</Button>
                    else
                        return <Button id={record.userId} name={record.userStatus} size='small' type='danger' onClick={this.freezeUser}>Freeze</Button>
                }
            }
        ];
        return (
            <Row>
                <Table rowKey={record => record.userId} bordered={true} columns={columns} dataSource={this.state.user} />
            </Row>
        );
    }
}

export default UserManage;
