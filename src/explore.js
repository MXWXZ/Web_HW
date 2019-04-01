import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from './component/Header';
require('./css/main.css');

class Explore extends Component {
    render() {
        return (
            <div>
                <Header current='1' />
                <div className="content">
                    <div className="show">
                        <ul>
                            <li>
                                <Link to="/book1" target="_blank">
                                    <img src={require("./img/book1.jpg")} alt="The Man Who Changed China" />
                                </Link>
                                <div className="bookMation">
                                    <h3><Link to="/book1" target="_blank">The Man Who Changed China</Link></h3>
                                    <p className="buy">&yen;48<a href="book1.html" target="_blank">Buy</a> </p>
                                    <p className="introduce">The must read book for all mogicians, you can feel your lifetime losing. No read no human.</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}

export default Explore;
