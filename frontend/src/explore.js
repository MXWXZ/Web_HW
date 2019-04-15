import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Table, Input, Row } from 'antd'

const columns = [{
    title: 'Image',
    dataIndex: 'img',
    align: 'center',
    width: 150,
    render: img => (
        <img className='book-img' src={'/img/' + img} alt={img} />
    )
}, {
    title: 'Name',
    dataIndex: 'name',
    align: 'center',
    sorter: (a, b) => a.name.localeCompare(b.name),
    render: (text, record) => (<Link className='book-title' to={'/book/' + record.key.toString()}>{text}</Link>)
}, {
    title: 'Author',
    dataIndex: 'author',
    align: 'center',
    sorter: (a, b) => a.author.localeCompare(b.author),
}, {
    title: 'ISBN',
    dataIndex: 'isbn',
    align: 'center',
    sorter: (a, b) => a.isbn.localeCompare(b.isbn),
}, {
    title: 'Amount',
    dataIndex: 'amount',
    align: 'center',
    sorter: (a, b) => a.amount - b.amount,
}, {
    title: 'Price',
    dataIndex: 'price',
    align: 'center',
    sorter: (a, b) => a.price - b.price,
    render: price => ((price / 100).toFixed(2))
}];

const Search = Input.Search;

class Explore extends Component {
    state = {
        book: [],
        bookSave: []
    }

    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        fetch('/BookInfo')
            .then(res => res.json())
            .then(data => {
                this.setState({
                    book: data,
                    bookSave: data
                })
            });
    }

    handleChange() {
        let pattern = document.getElementById('search').value;
        let list = this.state.bookSave.filter((item) => {
            return item.name.indexOf(pattern) !== -1;
        })
        this.setState({
            book: list
        })
    }

    render() {
        return (
            <div>
                <Row style={{ paddingBottom: '20px' }}>
                    <Search id='search' placeholder='search book' onChange={this.handleChange} style={{ width: 300 }} />
                </Row>
                <Row>
                    <Table bordered={true} columns={columns} dataSource={this.state.book} />
                </Row>
            </div>
        );
    }
}

export default Explore;
