import React, { Component } from 'react';
import { Modal, Button } from 'antd';
import { message, Form, Icon, Input, Checkbox } from 'antd';
import axios from 'axios';
import Qs from 'qs'
import Cookies from 'universal-cookie';

const cookies = new Cookies();

/*
    Signin form
*/
class Signin extends Component {
    state = {
        loading: false,
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({ loading: true });
        this.props.form.validateFields((err, values) => {
            if (!err) {
                axios.post('/api/tokens', Qs.stringify(values))
                    .then(response => {
                        this.setState({ loading: false });

                        let data = response.data;
                        if (data.code !== 0) {
                            message.error(data.msg);
                        } else {
                            cookies.set('userId', data.data.userId);
                            cookies.set('userName', data.data.userName);
                            cookies.set('userEmail', data.data.userEmail);
                            cookies.set('userPermission', data.data.userPermission);
                            message.success("Sign in success!");
                            setTimeout(() => { window.location.reload(); }, 1000);
                        }
                    })
            }
        });
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
                        getFieldDecorator('userPassword', { rules: [{ required: true, message: 'Please input your Password!' }] })(
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
        confirmDirty: false,
        loading: false
    }

    handleConfirmBlur = (e) => {
        const value = e.target.value;
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
    }

    validateUserName = (rule, value, callback) => {
        if (value) {
            axios.get(`/api/userVerify`, {
                params: {
                    userName: value
                }
            })
                .then(res => {
                    if (res.data.code !== 0)
                        callback('Username already exists!');
                    else
                        callback();
                });
        } else {
            callback();
        }
    }

    validateEmail = (rule, value, callback) => {
        if (value) {
            axios.get(`/api/userVerify`, {
                params: {
                    userEmail: value
                }
            })
                .then(res => {
                    if (res.data.code !== 0)
                        callback('Email already exists!');
                    else
                        callback();
                });
        } else {
            callback();
        }
    }

    compareToFirstPassword = (rule, value, callback) => {
        if (value && value !== this.props.form.getFieldValue('userPassword')) {
            callback('Two passwords that you enter is inconsistent!');
        }
        callback();
    }

    validateToNextPassword = (rule, value, callback) => {
        if (value && this.state.confirmDirty) {
            this.props.form.validateFields(['confirm'], { force: true });
        }
        callback();
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({ loading: true });
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                axios.post('/api/users', Qs.stringify(values))
                    .then(response => {
                        this.setState({ loading: false });

                        let data = response.data;
                        if (data.code !== 0) {
                            message.error(data.msg);
                        } else {
                            message.success("Sign up success!");
                            setTimeout(() => { window.location.reload(); }, 1000);
                        }
                    })
            }
        });
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <Form onSubmit={this.handleSubmit}>
                <Form.Item>
                    {
                        getFieldDecorator('userEmail', {
                            rules: [{ type: 'email', message: 'The input is not valid E-mail!' }, { required: true, message: 'Please input your E-mail!' },
                            { max: 32, message: 'E-mail length should be less than 32!' }, { validator: this.validateEmail }],
                            validateTrigger: 'onBlur'
                        })(
                            <Input prefix={<Icon type="mail" className='sign-icon' />} placeholder='Email' />
                        )
                    }
                </Form.Item>
                <Form.Item>
                    {
                        getFieldDecorator('userName', {
                            rules: [{ required: true, message: 'Please input your username!' }, { validator: this.validateUserName },
                            { max: 32, message: 'Username length should be less than 32!' }],
                            validateTrigger: 'onBlur'
                        })(
                            <Input prefix={<Icon type='user' className='sign-icon' />} placeholder='Username' />
                        )
                    }
                </Form.Item>
                <Form.Item>
                    {
                        getFieldDecorator('userPassword', {
                            rules: [{ required: true, message: 'Please input your Password!' }, { validator: this.validateToNextPassword },
                            { min: 6, message: 'Password length should be larger than 6!' }]
                        })(
                            <Input prefix={<Icon type='lock' className='sign-icon' />} type='password' placeholder='Password' />
                        )
                    }
                </Form.Item>
                <Form.Item>
                    {
                        getFieldDecorator('confirm', {
                            rules: [{ required: true, message: 'Please confirm your password!', }, { validator: this.compareToFirstPassword }]
                        })(
                            <Input prefix={<Icon type='lock' className='sign-icon' />} type='password' placeholder='Confirm password' onBlur={this.handleConfirmBlur} />
                        )
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
    @param  id: button id
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
                <Button id={typeof this.props.id !== "undefined" ? this.props.id : null} className='nav-button' type={this.props.type} onClick={this.showModal}>{this.props.text}</Button>
                <Modal visible={this.state.visible} title={this.props.text} onCancel={this.handleCancel} className='sign-modal' footer={null}>
                    {<this.props.form />}
                </Modal>
            </div>
        );
    }
}

const WrappedSigninForm = Form.create({ name: 'signin_form' })(Signin);
const WrappedSignupForm = Form.create({ name: 'signup_form' })(Signup);

class SignBar extends Component {
    render() {
        return (
            <div style={{ float: 'right' }}>
                <SignModal type='primary' text='Sign up' form={WrappedSignupForm} />
                <SignModal id='signin' type='dashed' text='Sign in' form={WrappedSigninForm} />
            </div>
        );
    }
}

export default SignBar;