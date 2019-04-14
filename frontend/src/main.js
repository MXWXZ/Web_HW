import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from './component/Header';
require('./css/main.css');

class Main extends Component {
    render() {
        return (
            <div>
                <Header current='0' />

                <div className="recom" style={{ background: 'url(' + require('./img/bg.gif') + ') no-repeat center top' }}>
                    <div className="recom_wrap">
                        <div className="title">
                            <h2>
                                <Link to="/book1" target="_blank">
                                    <i>The Man Who Changed China</i>
                                </Link>
                            </h2>
                            <p>You should sacrifice 1s for the elder...</p>
                        </div>
                        <div className="book">
                            <div className="book_wrap">
                                <ul>
                                    <li>
                                        <Link to="/book1" target="_blank">
                                            <img src={require('./img/i1km85x0zbc97cbr.jpg')} alt="The Man Who Changed China" />
                                        </Link>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div className="waiting">
                            <h2>Coming Soon, be patient!</h2>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Main;
