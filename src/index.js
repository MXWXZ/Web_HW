import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from "react-router-dom";
import Main from './main';
import "antd/dist/antd.css";
require('./css/main.css');

const StoreRouter = (
    <Router>
        <Route exact path="/" component={Main} />
    </Router>
);

ReactDOM.render(StoreRouter, document.getElementById('root'));
