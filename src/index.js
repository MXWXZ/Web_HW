import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from "react-router-dom";
import Main from './main';
import Signup from './signup';
import Explore from './explore';
import Book1 from './book1';

const StoreRouter = (
    <Router>
        <Route exact path="/" component={Main} />
        <Route exact path="/explore" component={Explore} />
        <Route exact path="/book1" component={Book1} />
        <Route path="/signup" component={Signup} />
    </Router>
);

ReactDOM.render(StoreRouter, document.getElementById('root'));
