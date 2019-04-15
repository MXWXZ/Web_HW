import React, { Component } from 'react';
import { Modal, Button } from 'antd';
import { Form, Icon, Input, Checkbox } from 'antd';

/*
    Signin form
*/
class Signin extends Component {
    state = {
        loading: false
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({ loading: true });
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
        this.setState({ loading: false });
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <Form onSubmit={this.handleSubmit}>
                <Form.Item>
                    {
                        getFieldDecorator('userName', { rules: [{ required: true, message: 'Please input your username!' }] })(
                            <Input prefix={<Icon type='user' className='sign-icon' />} placeholder='Username' />)
                    }
                </Form.Item>
                <Form.Item style={{ marginBottom: '15px' }}>
                    {
                        getFieldDecorator('password', { rules: [{ required: true, message: 'Please input your Password!' }] })(
                            <Input prefix={<Icon type='lock' className='sign-icon' />} type='password' placeholder='Password' />)
                    }
                </Form.Item>
                <Form.Item style={{ marginBottom: '0' }}>
                    {
                        getFieldDecorator('remember', { valuePropName: 'checked', initialValue: false })(
                            <Checkbox style={{ float: 'left' }}>Remember me</Checkbox>)
                    }
                    <Button type='primary' htmlType='submit' loading={this.state.loading} className='login-form-button'>Sign in</Button>
                </Form.Item>
            </Form>
        );
    }
}

/*
    Signup form
*/
class Signup extends Component {
    state = {
        loading: false
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({ loading: true });
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
        this.setState({ loading: false });
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <Form onSubmit={this.handleSubmit}>
                <Form.Item>
                    {
                        getFieldDecorator('userName', { rules: [{ required: true, message: 'Please input your username!' }] })(
                            <Input prefix={<Icon type='user' className='sign-icon' />} placeholder='Username' />)
                    }
                </Form.Item>
                <Form.Item style={{ marginBottom: '15px' }}>
                    {
                        getFieldDecorator('password', { rules: [{ required: true, message: 'Please input your Password!' }] })(
                            <Input prefix={<Icon type='lock' className='sign-icon' />} type='password' placeholder='Password' />)
                    }
                </Form.Item>
                <Form.Item style={{ marginBottom: '0' }}>
                    <Button type='primary' htmlType='submit' loading={this.state.loading} className='login-form-button'>Sign up</Button>
                </Form.Item>
            </Form>
        );
    }
}

/*
    Show modal
    @param  type: button type
    @param  text: button text
    @param  form: modal form
*/
class SignModal extends Component {
    state = {
        visible: false,
    }

    showModal = () => {
        this.setState({ visible: true });
    }

    handleCancel = () => {
        this.setState({ visible: false });
    }

    render() {
        return (
            <div style={{ display: 'inline' }}>
                <Button className='nav-button' type={this.props.type} onClick={this.showModal}>{this.props.text}</Button>
                <Modal visible={this.state.visible} title={this.props.text} onCancel={this.handleCancel} className='sign-modal' footer={null}>
                    {<this.props.form />}
                </Modal>
            </div>
        );
    }
}

const WrappedSigninForm = Form.create({ name: 'signin_form' })(Signin);
const WrappedSignupForm = Form.create({ name: 'signup_form' })(Signup);

class Sign extends Component {
    render() {
        return (
            <div style={{ float: 'right' }}>
                <SignModal type='dashed' text='Sign in' form={WrappedSigninForm} />
                <SignModal type='primary' text='Sign up' form={WrappedSignupForm} />
            </div>
        );
    }
}

export default Sign;