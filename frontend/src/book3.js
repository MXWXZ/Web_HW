import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from './component/Header';
require('./css/main.css');

class Book3 extends Component {
    render() {
        return (
            <div>
                <Header current='-1' />
                <div className="content">
                    <div className="bookInfor">
                        <div className="bookL">
                            <s style={{ background: 'url(' + require('./img/sjicon.png') + ') no-repeat' }}>Bio</s>
                            <Link to=''>
                                <img src={require("./img/f1ohtudwcx20ie3s.jpg")} alt="No Longer Human" />
                            </Link>
                        </div>
                        <div className="bookR">
                            <div className="bookinf01">
                                <div className="bookname">
                                    <h2><Link to=''>No Longer Human</Link></h2>
                                    <span>10.0</span> </div>
                                <p>
                                    <span className="author" style={{ marginRight: '12px' }}>Author: Osamu Dazai</span>
                                    <span>Press: Shanghai Translation Publishing House</span>
                                </p>
                            </div>
                            <div className="bookinf02">
                                <div className="left">
                                    <p>
                                        <i className="price">&yen;25</i>
                                    </p>
                                    <span className="buyBook" btn="buy_book">Buy</span>
                                </div>
                            </div>
                            <div className="bookinf03">
                                <p>This novel, despite being serialized as a work of fiction in 1948, is narrated in the first person and contains several elements which betray an autobiographical basis, such as suicide—a recurring theme in the author's life. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Book3;
