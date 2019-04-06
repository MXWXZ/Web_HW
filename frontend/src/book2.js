import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from './component/Header';
require('./css/main.css');

class Book2 extends Component {
    render() {
        return (
            <div>
                <Header current='-1' />
                <div className="content">
                    <div className="bookInfor">
                        <div className="bookL">
                            <s style={{ background: 'url(' + require('./img/sjicon.png') + ') no-repeat' }}>Bio</s>
                            <Link to=''>
                                <img src={require("./img/book2.jpg")} alt="Nineteen Eighty-Four" />
                            </Link>
                        </div>
                        <div className="bookR">
                            <div className="bookinf01">
                                <div className="bookname">
                                    <h2><Link to=''>Nineteen Eighty-Four</Link></h2>
                                    <span>10.0</span> </div>
                                <p>
                                    <span className="author" style={{ marginRight: '12px' }}>Author: George Orwell</span>
                                    <span>Press: Shanghai Translation Publishing House</span>
                                </p>
                            </div>
                            <div className="bookinf02">
                                <div className="left">
                                    <p>
                                        <i className="price">&yen;19.5</i>
                                    </p>
                                    <span className="buyBook" btn="buy_book">Buy</span>
                                </div>
                            </div>
                            <div className="bookinf03">
                                <p>The novel is set in the year 1984 when most of the world population have become victims of perpetual war, omnipresent government surveillance and propaganda. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Book2;
