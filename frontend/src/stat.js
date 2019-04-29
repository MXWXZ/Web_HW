import React, { Component } from 'react';
import { Statistic, Row, Col, Card } from 'antd';
import axios from 'axios';
import Cookies from 'universal-cookie';

const cookies = new Cookies();

class Stat extends Component {
    state = {
        userId: cookies.get('userId'),
        totOrders: 0,
        totBooks: 0,
        totMoney: 0
    }

    componentDidMount() {
        axios.get(`/api/stat`, {
            params: {
                userId: this.state.userId
            }
        })
            .then(res => {
                const data = res.data.data;
                console.log(data)
                this.setState({
                    totOrders: data.totOrders,
                    totBooks: data.totBooks,
                    totMoney: data.totMoney
                })
            });
    }

    render() {
        return (
            <Row gutter={16}>
                <Col span={8}>
                    <Card>
                        <Statistic title="Total orders" value={this.state.totOrders} />
                    </Card>
                </Col>
                <Col span={8}>
                    <Card>
                        <Statistic title="Total books" value={this.state.totBooks} />
                    </Card>
                </Col>
                <Col span={8}>
                    <Card>
                        <Statistic title="Total spent money" value={this.state.totMoney} precision={2} />
                    </Card>
                </Col>
            </Row>
        );
    }
}

export default Stat;
