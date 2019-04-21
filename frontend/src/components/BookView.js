import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Divider, Table, Input, Row } from 'antd'
import axios from 'axios';

const Search = Input.Search;

class BookView extends Component {
    state = {
        book: [],
        bookSave: []
    }

    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        axios.get(`/api/books`)
            .then(res => {
                this.setState({
                    book: res.data.data,
                    bookSave: res.data.data
                })
            });
    }

    handleChange() {
        let pattern = document.getElementById('search').value;
        let list = this.state.bookSave.filter((item) => {
            return item.bookName.indexOf(pattern) !== -1;
        })
        this.setState({
            book: list
        })
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
                title: 'Author',
                dataIndex: 'bookAuthor',
                align: 'center',
                sorter: (a, b) => a.bookAuthor.localeCompare(b.bookAuthor),
            }, {
                title: 'ISBN',
                dataIndex: 'bookIsbn',
                align: 'center',
                sorter: (a, b) => a.bookIsbn.localeCompare(b.bookIsbn),
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
            }
        ];
        if (this.props.showAction !== undefined) {
            columns.push({
                title: 'Action',
                align: 'center',
                render: (text, record) => (
                    <span>
                        <Button size='small'>Edit</Button>
                        <Divider type="vertical" />
                        <Button size='small' type='danger'>Delete</Button>
                    </span>
                )
            });
        }
        return (
            <div>
                <Row style={{ paddingBottom: '20px' }}>
                    <Search id='search' placeholder='search book' onChange={this.handleChange} style={{ width: 300 }} />
                </Row>
                <Row>
                    <Table rowKey={record => record.bookId} bordered={true} columns={columns} dataSource={this.state.book} />
                </Row>
            </div>
        );
    }
}

export default BookView;