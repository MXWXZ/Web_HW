import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from './component/Header';
import Button from '@material-ui/core/Button';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TextField from '@material-ui/core/TextField';
require('./css/main.css');

let counter = 0;
function createData(name, author, price, isbn, img, url) {
    counter += 1;
    return { id: counter, name, author, price, isbn, img, url };
}
let order = {
    name: true,
    author: true,
    price: true,
    isbn: true
}
let orderBy = 'name'
class Explore extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [
                createData('The Man Who Changed China', 'Robert Lawrence Kuhn', 4800, '‎7-5327-3654-7518', require('./img/book1.jpg'), '/book1'),
                createData('Nineteen Eighty-Four', 'George Orwell', 1950, '‎9-7875-4471-1647', require('./img/book2.jpg'), '/book2'),
                createData('No Longer Human', 'Osamu Dazai', 2500, '‎9-7875-0638-0263', require('./img/book3.jpg'), '/book3'),
            ],
            booksCp: [
                createData('The Man Who Changed China', 'Robert Lawrence Kuhn', 4800, '‎7-5327-3654-7518', require('./img/book1.jpg'), '/book1'),
                createData('Nineteen Eighty-Four', 'George Orwell', 1950, '‎9-7875-4471-1647', require('./img/book2.jpg'), '/book2'),
                createData('No Longer Human', 'Osamu Dazai', 2500, '‎9-7875-0638-0263', require('./img/book3.jpg'), '/book3'),
            ]
        };
    }
    handleLink(index) {
        return "/detail/" + index
    }
    handleSort(index) {
        orderBy = index
        order[index] = !order[index]
        let list = []
        for (let i = 0; i < this.state.books.length; i++) {
            list.push(this.state.books[i])
        }
        list.sort(this.sort)
        this.setState({
            books: list
        })
    }
    sort(a, b) {
        let res = 0;
        if (a[orderBy] < b[orderBy]) {
            res = -1
        } else if (a[orderBy] > b[orderBy]) {
            res = 1
        } else {
            res = 0
        }
        if (!order[orderBy]) {
            res = 0 - res
        }
        return res
    }
    handleChange() {
        let pattern = document.getElementById('filter').value
        let list = this.state.booksCp.filter((item) => {
            return item.name.indexOf(pattern) !== -1
        })
        this.setState({
            books: list
        })
    }
    render() {
        return (
            <div>
                <Header current='1' />
                <div className="content">
                    <div className="show">
                        <TextField style={{ marginBottom: '20px' }} label="Search" id={'filter'} onChange={() => this.handleChange()} />
                        <Table className="table">
                            <TableHead>
                                <TableRow>
                                    <TableCell align="center">
                                        <Button>Image</Button>
                                    </TableCell>
                                    <TableCell align="center">
                                        <Button onClick={() => { this.handleSort("name") }}>Name</Button>
                                    </TableCell>
                                    <TableCell align="center">
                                        <Button onClick={() => { this.handleSort("author") }}>Author</Button>
                                    </TableCell>
                                    <TableCell align="center">
                                        <Button onClick={() => { this.handleSort("price") }}>Price</Button>
                                    </TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {this.state.books.map((item, index) => {
                                    return (
                                        <TableRow key={index} >
                                            <TableCell component="th" scope="row" >
                                                <img className='showimg' src={item.img} alt={item.name} />
                                            </TableCell>
                                            <TableCell align="center">
                                                <Link to={item.url} target="_blank" className="showtitle">{item.name}</Link>
                                            </TableCell>
                                            <TableCell align="center">
                                                {item.author}
                                            </TableCell>
                                            <TableCell align="center">
                                                {item.price / 100}
                                            </TableCell>
                                        </TableRow>
                                    )
                                })}
                            </TableBody>
                        </Table>
                    </div>
                </div>
            </div>
        );
    }
}

export default Explore;
