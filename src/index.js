import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from "react-router-dom";
import Main from './main';
import "antd/dist/antd.css";
// import Signup from './signup';
// import Cart from './cart';
// import Explore from './explore';
// import Book1 from './book1';
// import Book2 from './book2';
// import Book3 from './book3';

const StoreRouter = (
    <Router>
        <Route exact path="/" component={Main} />
        {/* <Route path="/explore" component={Explore} />
        <Route path="/book1" component={Book1} />
        <Route path="/book2" component={Book2} />
        <Route path="/book3" component={Book3} />
        <Route path="/Cart" component={Cart} />
        <Route path="/signup" component={Signup} /> */}
    </Router>
);

ReactDOM.render(StoreRouter, document.getElementById('root'));
