import React, { Component } from 'react';
import { Statistic, Row, Col, Card, DatePicker, message } from 'antd';
import moment from 'moment';
import axios from 'axios';

const { RangePicker } = DatePicker;

class Stat extends Component {
    state = {
        userId: sessionStorage.getItem('userId'),
        totOrders: 0,
        totBooks: 0,
        totMoney: 0,
        minTime: null,
        maxTime: null
    }

    constructor(props) {
        super(props);
        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {
        axios.get(`/api/stat`, {
            params: {
                userId: this.state.userId
            },
            headers: {
                token: sessionStorage.getItem('token')
            }
        })
            .then(res => {
                if (res.data.code === 0) {
                    const data = res.data.data;
                    this.setState({
                        totOrders: data.totOrders,
                        totBooks: data.totBooks,
                        totMoney: data.totMoney,
                        minTime: data.minTime,
                        maxTime: data.maxTime
                    })
                }
            });
    }

    onChange(date, dateString) {
        const mint = date[0].unix();
        const maxt = date[1].unix();

        axios.get(`/api/stat`, {
            params: {
                userId: this.state.userId,
                minTime: mint,
                maxTime: maxt
            },
            headers: {
                token: sessionStorage.getItem('token')
            }
        })
            .then(res => {
                if (res.data.code !== 0) {
                    message.error(res.data.msg);
                    this.setState({
                        totOrders: 0,
                        totBooks: 0,
                        totMoney: 0,
                    })
                } else {
                    message.success('Data updated');
                    const data = res.data.data;
                    this.setState({
                        totOrders: data.totOrders,
                        totBooks: data.totBooks,
                        totMoney: data.totMoney,
                    })
                }
            });

        this.setState({
            minTime: mint,
            maxTime: maxt
        })
    }

    render() {
        return (
            <div>
                <Row type='flex' justify='center'>
                    <Col>
                        <RangePicker value={[moment.unix(this.state.minTime), moment.unix(this.state.maxTime)]} onChange={this.onChange}
                            allowClear={false}
                            ranges={{ Today: [moment().startOf('day'), moment().endOf('day')], 'This Month': [moment().startOf('month'), moment().endOf('month')] }} />
                    </Col>
                </Row>
                <Row gutter={16} style={{ marginTop: '30px' }}>
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
                            <Statistic title="Total spent money" value={'¥ ' + (this.state.totMoney / 100).toFixed(2)} precision={2} />
                        </Card>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default Stat;
