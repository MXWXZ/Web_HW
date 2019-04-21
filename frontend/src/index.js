import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Main from './main';
import Home from './home';
import Explore from './explore';
import UserManage from './usermanage';
import BookManage from './bookmanage';
import 'antd/dist/antd.css';
require('./css/main.css');

const StoreRouter = (
    <Router>
        <Route exact path='/' render={(props) => <Main {...props} content={Home} default={'0'} />} />
        <Route exact path='/explore' render={(props) => <Main {...props} content={Explore} default={'1'} />} />
        <Route exact path='/usermanage' render={(props) => <Main {...props} content={UserManage} />} />
        <Route exact path='/bookmanage' render={(props) => <Main {...props} content={BookManage} />} />
    </Router>
);

ReactDOM.render(StoreRouter, document.getElementById('root'));
