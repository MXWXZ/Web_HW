import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Col, Row, Menu } from 'antd';
import SignBar from './Sign';
import AvatarBar from './Avatar';


/*
    Header
    @param  nav: navlist [{name,url}]
    @param  default: default select
*/
class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userId: sessionStorage.getItem('userId'),
            userName: sessionStorage.getItem('userName'),
            userPermission: sessionStorage.getItem('userPermission'),
        };
    }
    render() {
        const item = [];
        this.props.nav.forEach((nav, index) => {
            item.push(<Menu.Item key={index}><Link to={nav.url}>{nav.name}</Link></Menu.Item>)
        });
        return (
            <header id='header'>
                <Row>
                    <Col span={18} offset={3}>
                        <Col span={6}>
                            <Link className='logo' to='/'>
                                <img alt='logo' className='logo' src={require('../img/logo.png')} />
                            </Link>
                        </Col>
                        <Col span={12}>
                            <Menu mode='horizontal' style={{ borderBottom: 'none' }} defaultSelectedKeys={typeof this.props.default !== "undefined" ? [this.props.default] : ['']}>
                                {item}
                            </Menu>
                        </Col>
                        <Col span={6}>
                            {this.state.userId ? <AvatarBar userName={this.state.userName} userPermission={this.state.userPermission} /> : <SignBar />}
                        </Col>
                    </Col>
                </Row>
            </header >
        );
    }
}

export default Header;
