import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Modal, Button, Table, Typography, message, InputNumber } from 'antd';
import axios from 'axios';
import Qs from 'qs';
import Cookies from 'universal-cookie';

const { Text } = Typography;
const confirm = Modal.confirm;
const cookies = new Cookies();

class Cart extends Component {
    state = {
        selectedRowKeys: [],
        book: [],
        totPrice: 0
    }

    constructor(props) {
        super(props);
        this.onBlurAmount = this.onBlurAmount.bind(this);
        this.onDeleteItem = this.onDeleteItem.bind(this);
        this.onBuy = this.onBuy.bind(this);
    }

    componentDidMount() {
        axios.get(`/api/cart`, {
            params: {
                userId: cookies.get('userId'),
            }
        })
            .then(res => {
                this.setState({
                    book: res.data.data,
                })
            });
    }

    onBlurAmount(e) {
        const target = e.target;
        let newbook = this.state.book;
        newbook[target.name]['cartAmount'] = target.value;
        this.setState({ book: newbook });

        axios.post(`/api/cart`, Qs.stringify({
            userId: cookies.get('userId'),
            bookId: newbook[target.name]['bookId'],
            cartAmount: target.value
        }))
            .then(res => {
                if (res.data.code !== 0) {
                    message.error(res.data.msg);
                } else {
                    message.success("Change cart item successfully!");
                }
            });
    }

    onDeleteItem(e) {
        const target = e.target;
        axios.delete(`/api/cart`, {
            params: {
                userId: cookies.get('userId'),
                bookId: this.state.book[target.name]['bookId'],
            }
        })
            .then(res => {
                if (res.data.code !== 0) {
                    message.error(res.data.msg);
                } else {
                    message.success("Delete cart item successfully!");
                }
            });

        let newbook = this.state.book;
        newbook.splice(target.name, 1);
        this.setState({ book: newbook });
    }

    onBuy(e) {
        let tot = 0;
        let booklist = [];
        this.state.selectedRowKeys.forEach(item => {
            const tmp = this.state.book[item];
            booklist.push(tmp['cartId']);
            tot += tmp['bookPrice'] / 100 * tmp['cartAmount'];
        });
        confirm({
            title: 'Do you want to pay now?',
            content: 'Total price: ¥' + tot,
            centered: true,
            onOk() {
                return new Promise((resolve, reject) => {
                    axios.put(`/api/orders`, {
                        userId: cookies.get('userId'),
                        cartId: booklist,
                    }).then(res => {
                        if (res.data.code !== 0)
                            message.error(res.data.msg);
                        else
                            message.success("Paid successfully!");
                        setTimeout(() => { window.location.reload(); }, 1000);
                    });
                    return reject;
                }).catch(() => console.log('Oops errors!'));
            },
        });
    }

    onSelectChange = (selectedRowKeys) => {
        let tot = 0;
        selectedRowKeys.forEach(item => {
            const tmp = this.state.book[item];
            tot += tmp['bookPrice'] / 100 * tmp['cartAmount'];
        });

        this.setState({
            selectedRowKeys,
            totPrice: tot
        });
    }

    render() {
        const columns = [
            {
                title: 'Image',
                dataIndex: 'bookImg',
                align: 'center',
                width: 150,
                render: bookImg => (
                    <img className='book-img' src={'/img/' + bookImg} alt={bookImg} />
                )
            }, {
                title: 'Name',
                dataIndex: 'bookName',
                align: 'center',
                sorter: (a, b) => a.bookName.localeCompare(b.bookName),
                render: (text, record) => (<Link className='book-title' to={'/book/' + record.bookId.toString()}>{text}</Link>)
            }, {
                title: 'Price',
                dataIndex: 'bookPrice',
                align: 'center',
                sorter: (a, b) => a.bookPrice - b.bookPrice,
                render: (text, record) => ((text / 100).toFixed(2))
            }, {
                title: 'Amount',
                dataIndex: 'cartAmount',
                align: 'center',
                sorter: (a, b) => a.bookAmount - b.bookAmount,
                render: (text, record) => (<InputNumber name={record.key} min={1} max={record.bookAmount} defaultValue={text} onBlur={this.onBlurAmount} />)
            }, {
                title: 'Total',
                dataIndex: 'totalPrice',
                align: 'center',
                sorter: (a, b) => a.totalPrice - b.totalPrice,
                render: (text, record) => ((record.bookPrice / 100 * record.cartAmount).toFixed(2))
            }, {
                title: 'Action',
                align: 'center',
                render: (text, record) => (<Button name={record.key} size='small' type='danger' onClick={this.onDeleteItem}>delete</Button>)
            }
        ];

        const rowSelection = {
            selectedRowKeys: this.state.selectedRowKeys,
            onChange: this.onSelectChange,
        };

        return (
            <div>
                <Button type='primary' size='large' style={{ float: 'right', width: '100px', marginLeft: '20px' }}
                    onClick={this.onBuy} disabled={this.state.selectedRowKeys.length === 0 ? true : false}>Buy</Button>
                <Text className='cart-price' style={{ float: 'right' }}>Total: &yen; {this.state.totPrice.toFixed(2)}</Text>
                <div className='clear' />
                <Table rowSelection={rowSelection} rowKey={record => record.key} bordered={true} columns={columns} dataSource={this.state.book} style={{ marginTop: '10px' }}
                    pagination={false} />
            </div>
        );
    }
}

export default Cart;
