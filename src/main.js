import React, { Component } from 'react';
import { Layout } from 'antd';
import Header from './components/Header';

const { Content } = Layout;

/*
    Main page
    @param content: page content
    @param default: default select
*/
class Main extends Component {
    render() {
        const nav = [
            { name: 'Home', url: '/' },
            { name: 'Explore', url: '/explore' }
        ];
        return (
            <Layout className="layout">
                <Header nav={nav} default={this.props.default} />
                <Content style={{ padding: '0 50px' }}>
                    {this.props.content}
                </Content>
            </Layout>
        );
    }
}

export default Main;
