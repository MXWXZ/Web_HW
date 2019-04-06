import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Row, Menu } from 'antd';

/*
    Header
    @param  nav: navlist [{name,url}]
    @param  default: default select
*/
class Header extends Component {
    render() {
        const item = [];
        this.props.nav.forEach((nav, index) => {
            item.push(<Menu.Item key={index}>{nav.name}</Menu.Item>)
        });
        return (
            <Row>
                <Menu mode="horizontal" defaultSelectedKeys={this.props.default ? this.props.default : ''}>
                    {item}
                </Menu>
            </Row>
        );
    }
}

export default Header;
