import React, { Component } from 'react';
import { Layout } from 'antd';
import Header from './components/Header';


class Main extends Component {
    render() {
        const nav = [
            { name: 'Home', url: '/' },
            { name: 'Explore', url: '/explore' }
        ];
        return (
            <Layout className="layout">
                <Header nav={nav} />
            </Layout>
        );
    }
}

export default Main;
