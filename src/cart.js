import React, { Component } from 'react';
import Header from './component/Header';
require('./css/main.css');

class Cart extends Component {
    render() {
        return (
            <div>
                <Header current='-1' />
                <div className="content">
                    <div className="waiting">
                        <h2>Cart coming Soon, be patient!</h2>
                    </div>
                </div>
            </div>
        );
    }
}

export default Cart;
