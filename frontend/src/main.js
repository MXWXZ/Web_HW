import React, { Component } from 'react';
import { Layout, Row, Col } from 'antd';
import Header from './components/Header';

const { Content, Footer } = Layout;

const nav = [
    { name: 'Home', url: '/' },
    { name: 'Explore', url: '/explore' }
];

/*
    Main page
    @param  content: page content
    @param  default: default select
    @param  content: page content
*/
class Main extends Component {
    render() {
        return (
            <Layout className='layout'>
                <Header nav={nav} default={this.props.default} />
                <Content style={{ paddingTop: '20px' }}>
                    <Row>
                        <Col span={18} offset={3}>
                            <div style={{ background: '#fff', padding: 24, minHeight: 280 }}>
                                {<this.props.content />}
                            </div>
                        </Col>
                    </Row>
                </Content>
                <Footer style={{ textAlign: 'center' }}>
                    Designed by Xinzhe Wang
                </Footer>
            </Layout>
        );
    }
}

export default Main;
