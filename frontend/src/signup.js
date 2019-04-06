import React, { Component } from 'react';
require('./css/signup.css');

class Signup extends Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({ [event.target.name]: event.target.value });
    }

    handleSubmit(event) {
        event.preventDefault();
        alert(this.state.username);
    }

    render() {
        return (
            <div className="sign_wrap">
                <div className="sign_title">
                    <h1>Jiang's Store</h1>
                </div>
                <div className="sign_c">
                    <form onSubmit={this.handleSubmit}>
                        <div className="sign">
                            <div className="row">Username: <input name="username" id="username" className="inp" type="text" placeholder="Username" onChange={this.handleChange} /></div>
                            <div className="row">Password: <input id="password" name="password" className="inp" type="text" placeholder="Password" onChange={this.handleChange} /></div>
                        </div>
                        <input type="submit" id="submit" className="btn_o" value="Sign up" />
                    </form>
                </div>
            </div>
        );
    }
}

export default Signup;
