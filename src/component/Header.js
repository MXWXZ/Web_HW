import React, { Component } from 'react';
import { Link } from 'react-router-dom';

/*
    Navbar element
    name, url, iscurrent
*/
class NavbarElement extends Component {
    render() {
        return (
            <li className={this.props.iscurrent ? 'current' : null}><Link to={this.props.url}>{this.props.name}</Link></li>
        );
    }
}

class Navbar extends Component {
    render() {
        const element = [];
        this.props.nav.forEach((nav, index) => {
            let iscurrent = false;
            if (index === parseInt(this.props.current))
                iscurrent = true;
            element.push(<NavbarElement key={index} name={nav.name} url={nav.url} iscurrent={iscurrent} />)
        });
        return (
            <div className="header_nav">
                <ul>{element}</ul>
            </div>
        );
    }
}

class Header extends Component {
    render() {
        const nav = [
            { name: 'Index', url: '/' },
            { name: 'Explore', url: '/explore' }
        ];
        return (
            <div className="header">
                <div className="header_bar">
                    <h1>Jiang's Store</h1>
                    <Navbar nav={nav} current={this.props.current} />
                    <div className="header_login">
                        <Link to="/signup"><span style={{ display: "block" }}>Sign up</span></Link>
                    </div>
                </div>
            </div>
        );
    }
}

export default Header;
