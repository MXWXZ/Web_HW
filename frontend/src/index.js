import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom';
import Main from './main';
import Home from './home';
import Explore from './explore';
import UserManage from './usermanage';
import BookManage from './bookmanage';
import StatManage from './statmanage';
import Cart from './cart';
import Stat from './stat';
import Order from './order';
import BookDetail from './bookdetail';
import 'antd/dist/antd.css';
require('./css/main.css');

class AuthRoute extends React.Component {
    render() {
        const { component: Component, ...rest } = this.props;
        const isLogged = sessionStorage.getItem("userId") != null &&
            sessionStorage.getItem("userName") != null &&
            sessionStorage.getItem("userPermission") === this.props.permission &&
            sessionStorage.getItem("token") != null;

        if (!isLogged) {
            return <Route {...rest} render={(props) => <Redirect to='/' />} />
        } else {
            return <Route {...rest} render={this.props.render} />;
        }
    }
}

const StoreRouter = (
    <Router>
        <Route exact path='/' render={(props) => <Main {...props} content={Home} default={'0'} />} />
        <Route exact path='/explore' render={(props) => <Main {...props} content={Explore} default={'1'} />} />
        <AuthRoute permission='1' exact path='/usermanage' render={(props) => <Main {...props} content={UserManage} />} />
        <AuthRoute permission='1' exact path='/bookmanage' render={(props) => <Main {...props} content={BookManage} />} />
        <AuthRoute permission='1' exact path='/ordermanage' render={(props) => <Main {...props} content={Order} />} />
        <AuthRoute permission='1' exact path='/statmanage' render={(props) => <Main {...props} content={StatManage} />} />
        <AuthRoute permission='0' exact path='/stat' render={(props) => <Main {...props} content={Stat} />} />
        <AuthRoute permission='0' exact path='/cart' render={(props) => <Main {...props} content={Cart} />} />
        <AuthRoute permission='0' exact path='/order' render={(props) => <Main {...props} content={Order} />} />
        <Route exact path='/book/:bookId' render={(props) => <Main {...props} content={BookDetail} />} />
    </Router>
);

ReactDOM.render(StoreRouter, document.getElementById('root'));
