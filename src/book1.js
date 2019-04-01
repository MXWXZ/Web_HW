import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from './component/Header';
require('./css/main.css');

class Book1 extends Component {
    render() {
        return (
            <div>
                <Header current='-1' />
                <div className="content">
                    <div className="bookInfor">
                        <div className="bookL">
                            <s style={{ background: 'url(' + require('./img/sjicon.png') + ') no-repeat' }}>Bio</s>
                            <Link to=''>
                                <img src={require("./img/book1.jpg")} alt="The Man Who Changed China" />
                            </Link>
                        </div>
                        <div className="bookR">
                            <div className="bookinf01">
                                <div className="bookname">
                                    <h2><Link to=''>The Man Who Changed China</Link></h2>
                                    <span>10.0</span> </div>
                                <p>
                                    <span className="author" style={{ marginRight: '12px' }}>Author: Robert Lawrence Kuhn</span>
                                    <span>Press: Shanghai Translation Publishing House</span>
                                </p>
                            </div>
                            <div className="bookinf02">
                                <div className="left">
                                    <p>
                                        <i className="price">&yen;48</i>
                                    </p>
                                    <span className="buyBook" btn="buy_book">Buy</span>
                                </div>
                            </div>
                            <div className="bookinf03">
                                <p>The must read book for all mogicians, you can feel your lifetime losing. No read no human. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Book1;
