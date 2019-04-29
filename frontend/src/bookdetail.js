import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { message, Typography, Row, Col, InputNumber, Button } from 'antd';
import axios from 'axios';
import Qs from 'qs';
import Cookies from 'universal-cookie';

const { Text } = Typography;
const cookies = new Cookies();

class BookDetail extends Component {
    state = {
        bookId: 0,
        bookName: '',
        bookAuthor: '',
        bookPrice: 0,
        bookIsbn: '',
        bookAmount: 0,
        bookImg: '',
        bookDetail: ''
    }

    constructor(props) {
        super(props);
        this.addCart = this.addCart.bind(this);
    }

    componentDidMount() {
        const urlParam = this.props.match.params;
        axios.get(`/api/books`, {
            params: {
                bookId: urlParam.bookId
            }
        })
            .then(res => {
                const data = res.data.data;
                this.setState({
                    bookId: data.bookId,
                    bookName: data.bookName,
                    bookAuthor: data.bookAuthor,
                    bookPrice: data.bookPrice,
                    bookIsbn: data.bookIsbn,
                    bookAmount: data.bookAmount,
                    bookImg: data.bookImg,
                    bookDetail: data.bookDetail
                })
            });
    }

    addCart() {
        if (!cookies.get('userId')) {
            document.getElementById('signin').click();
        } else {
            let amount = document.getElementById('amount').value;
            axios.put(`/api/cart`, Qs.stringify({
                userId: cookies.get('userId'),
                bookId: this.state.bookId,
                cartAmount: amount
            })).then(res => {
                if (res.data.code !== 0) {
                    message.error(res.data.msg);
                } else {
                    message.success("Add to cart successfully!");
                }
            });
        }
    }

    render() {
        return (
            <Row>
                <Col span={6} className='book-detail-img' style={{ height: '350px' }}>
                    <img alt='cover' src={'/img/' + this.state.bookImg} style={{ height: '100%' }} />
                </Col>
                <Col span={18} className='book-detail-info'>
                    <div style={{ height: '180px' }}>
                        <Row>
                            <h1 style={{ float: 'left' }}>{this.state.bookName}</h1>
                            <Text type='secondary' style={{ float: 'right' }}>ISBN: {this.state.bookIsbn}</Text>
                            <div className='clear' />
                        </Row>
                        <Row>
                            <Text type='secondary'>{this.state.bookAuthor}</Text>
                        </Row>
                        <Row style={{ marginTop: '50px' }}>
                            <Text>{this.state.bookDetail}</Text>
                        </Row>
                    </div>
                    <div style={{ height: '170px' }}>
                        <Row style={{ paddingTop: '10px' }}>
                            <Text className='book-price'>&yen; {(this.state.bookPrice / 100).toFixed(2)}</Text>
                        </Row>
                        <Row style={{ marginTop: '10px' }}>
                            <Text style={{ marginRight: '10px' }}>Amount: </Text>
                            <InputNumber id='amount' min={1} max={this.state.bookAmount} defaultValue={1} />
                            <Text style={{ marginLeft: '20px' }}>Stock: {this.state.bookAmount}</Text>
                        </Row>
                        <Row style={{ marginTop: '20px' }}>
                            <Button size='large' onClick={this.addCart}>Add to cart</Button>
                        </Row>
                    </div>
                </Col>
            </Row>
        );
    }
}

export default withRouter(BookDetail);