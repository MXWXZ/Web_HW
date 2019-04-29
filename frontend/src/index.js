import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Main from './main';
import Home from './home';
import Explore from './explore';
import UserManage from './usermanage';
import BookManage from './bookmanage';
import OrderManage from './ordermanage';
import StatManage from './statmanage';
import Cart from './cart';
import Stat from './stat';
import Order from './order';
import BookDetail from './bookdetail';
import 'antd/dist/antd.css';
require('./css/main.css');

const StoreRouter = (
    <Router>
        <Route exact path='/' render={(props) => <Main {...props} content={Home} default={'0'} />} />
        <Route exact path='/explore' render={(props) => <Main {...props} content={Explore} default={'1'} />} />
        <Route exact path='/usermanage' render={(props) => <Main {...props} content={UserManage} />} />
        <Route exact path='/bookmanage' render={(props) => <Main {...props} content={BookManage} />} />
        <Route exact path='/ordermanage' render={(props) => <Main {...props} content={OrderManage} />} />
        <Route exact path='/statmanage' render={(props) => <Main {...props} content={StatManage} />} />
        <Route exact path='/stat' render={(props) => <Main {...props} content={Stat} />} />
        <Route exact path='/cart' render={(props) => <Main {...props} content={Cart} />} />
        <Route exact path='/order' render={(props) => <Main {...props} content={Order} />} />
        <Route exact path='/book/:bookId' render={(props) => <Main {...props} content={BookDetail} />} />
    </Router>
);

ReactDOM.render(StoreRouter, document.getElementById('root'));
