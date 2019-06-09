import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Divider, Table, Input, Row, message, Modal } from 'antd'
import axios from 'axios';

const Search = Input.Search;
const confirm = Modal.confirm;

class BookView extends Component {
    state = {
        book: [],
        bookSave: []
    }

    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.deleteBook = this.deleteBook.bind(this);
    }

    componentDidMount() {
        axios.get(`/api/books`)
            .then(res => {
                this.setState({
                    book: res.data.data,
                    bookSave: res.data.data
                })
            });
    }

    deleteBook(e) {
        let id = parseInt(e.target.id);
        let call = this;
        confirm({
            title: 'Are you sure delete this book?',
            content: 'This operation can not be undo!',
            okText: 'Yes',
            okType: 'danger',
            cancelText: 'No',
            onOk() {
                axios.delete(`/api/books`, {
                    params: {
                        bookId: id
                    }
                })
                    .then(res => {
                        if (res.data.code !== 0) {
                            message.error(res.data.msg);
                        } else {
                            let newbook = call.state.book;
                            for (let i = 0, len = newbook.length; i < len; ++i) {
                                if (newbook[i].bookId === id) {
                                    newbook.splice(i, 1);
                                    break;
                                }
                            }
                            call.setState({
                                book: newbook,
                                bookSave: newbook
                            })
                        }
                    });
            }
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
                    <img className='book-img' src={'/image/' + bookImg} alt={bookImg} />
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
                        <Button id={record.bookId} size='small'>Edit</Button>
                        <Divider type="vertical" />
                        <Button id={record.bookId} size='small' type='danger' onClick={this.deleteBook}>Delete</Button>
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