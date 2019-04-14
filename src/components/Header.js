import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Col, Row, Menu, Button } from 'antd';
import Sign from './Sign'

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
            <header id="header">
                <Row>
                    <Col span={16} offset={4}>
                        <Col span={6}>
                            <Link className="logo" to="/">
                                <img alt="logo" className="logo" src={require('../img/logo.png')} />
                            </Link>
                        </Col>
                        <Col span={12}>
                            <Menu mode="horizontal" style={{ borderBottom: "none" }} defaultSelectedKeys={this.props.default ? [this.props.default] : ['']}>
                                {item}
                            </Menu>
                        </Col>
                        <Col span={6}>
                            <Sign />
                        </Col>
                    </Col>
                </Row>
            </header >
        );
    }
}

export default Header;
