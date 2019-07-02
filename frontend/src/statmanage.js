import React, { Component } from 'react';
import { Table, Row, Col, DatePicker, message } from 'antd';
import moment from 'moment';
import axios from 'axios';

const { RangePicker } = DatePicker;

class StatList extends Component {
    state = {
        data: [],
    }

    componentWillReceiveProps(nextProps) {
        axios.get(nextProps.url, {
            params: {
                minTime: nextProps.minTime,
                maxTime: nextProps.maxTime,
            },
            headers: { token: sessionStorage.getItem('token') }
        })
            .then(res => {
                if (res.data.code !== 0) {
                    if (nextProps.showTip === 'true')
                        message.error(res.data.msg);
                    this.setState({
                        data: [],
                    })
                } else {
                    this.setState({
                        data: res.data.data,
                    })
                    if (nextProps.showTip === 'true')
                        message.success('Data updated');
                }
            });
    }

    render() {
        return (
            <Table rowKey={(_, index) => index} bordered={true} columns={this.props.columns} dataSource={this.state.data} />
        );
    }
}

class StatManage extends Component {
    state = {
        minTime: null,
        maxTime: null
    }

    constructor(props) {
        super(props);
        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {
        axios.get('/api/totstat', { headers: { token: sessionStorage.getItem('token') } })
            .then(res => {
                if (res.data.code === 0)
                    this.setState({
                        minTime: res.data.data.minTime,
                        maxTime: res.data.data.maxTime
                    });
            });
    }

    onChange(date, dateString) {
        this.setState({
            minTime: date[0].unix(),
            maxTime: date[1].unix()
        });
    }

    render() {
        const bookColumns = [
            {
                title: 'Name',
                dataIndex: 'bookName',
                align: 'center',
                sorter: (a, b) => a.bookName.localeCompare(b.bookName),
            }, {
                title: 'Amount',
                dataIndex: 'bookAmount',
                align: 'center',
                sorter: (a, b) => a.bookAmount - b.bookAmount,
            }, {
                title: 'Price',
                dataIndex: 'bookPrice',
                align: 'center',
                sorter: (a, b) => a.bookPrice - b.bookPrice,
                render: bookPrice => ((bookPrice / 100).toFixed(2))
            }, {
                title: 'Total',
                dataIndex: 'totPrice',
                align: 'center',
                sorter: (a, b) => a.totPrice - b.totPrice,
                render: (_, record) => ((record.bookPrice / 100 * record.bookAmount).toFixed(2))
            }
        ];

        const userColumns = [
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
                title: 'Amount',
                dataIndex: 'bookAmount',
                align: 'center',
                sorter: (a, b) => a.bookAmount - b.bookAmount,
            }, {
                title: 'Total',
                dataIndex: 'totPrice',
                align: 'center',
                sorter: (a, b) => a.totPrice - b.totPrice,
                render: totPrice => ((totPrice / 100).toFixed(2))
            }
        ];

        return (
            <div>
                <Row type='flex' justify='center'>
                    <Col>
                        <RangePicker value={[moment.unix(this.state.minTime), moment.unix(this.state.maxTime)]} onChange={this.onChange}
                            allowClear={false}
                            ranges={{ Today: [moment().startOf('day'), moment().endOf('day')], 'This Month': [moment().startOf('month'), moment().endOf('month')] }} />
                    </Col>
                </Row>
                <Row style={{ marginTop: '20px' }}>
                    <StatList showTip='true' url='/api/bookstat' minTime={this.state.minTime} maxTime={this.state.maxTime} columns={bookColumns} />
                </Row>
                <Row style={{ marginTop: '20px' }}>
                    <StatList url='/api/userstat' minTime={this.state.minTime} maxTime={this.state.maxTime} columns={userColumns} />
                </Row>
            </div>
        );
    }
}

export default StatManage;
