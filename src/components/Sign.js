import React, { Component } from 'react';
import { Modal, Button } from 'antd';
import { Form, Icon, Input, Checkbox } from 'antd';

class Sign extends Component {
    state = {
        loading: false,
        visible: false,
    }

    showModal = () => {
        this.setState({ visible: true });
    }

    handleOk = () => {
        this.setState({ loading: true });
        setTimeout(() => {
            this.setState({ loading: false, visible: false });
        }, 3000);
    }

    handleCancel = () => {
        this.setState({ visible: false });
    }

    render() {
        return (
            <div style={{ float: "right" }}>
                <Button className="nav-button" type="dashed" onClick={this.showModal}>Sign in</Button>
                <Modal visible={this.state.visible} title="Sign in" onOk={this.handleOk} onCancel={this.handleCancel}
                    style={{ textAlign: "center" }}
                    footer={null}
                >
                    <p>Some contents...</p>
                </Modal>
                <Button type="primary">Sign up</Button>
            </div>
        );
    }
}

export default Sign;