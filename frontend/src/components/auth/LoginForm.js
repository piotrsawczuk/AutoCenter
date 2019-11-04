import React, { Component } from 'react';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';

class LoginForm extends Component {
    
    state = {
        username: '',
        password: '',
        errors: {}
    };

    validate = (data) => {
        const errors = {};
        if (data.username.trim() === '') errors.username = 'Username cannot be empty';
        if (data.password.trim() === '') errors.password = 'Password cannot be empty';
        this.setState({errors});
        return Object.keys(errors).length === 0;
    }

    onSubmit = (e) => {
        const data = {
            username : this.state.username,
            password : this.state.password
        };
        const isValid = this.validate(data);
        if (isValid === true) this.props.onSubmit(data);
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        const { errors } = this.state;

        return (
            <Form onSubmit = {this.onSubmit}>
                <Form.Field>
                    <label>Username</label>
                    <input placeholder = 'Username' name='username' value = {this.state.username} onChange = {this.onChange} />
                    { !!errors.username && <InlineError text={errors.username} /> }
                </Form.Field>
                <Form.Field>
                    <label>Password</label>
                    <input placeholder = 'Password' type = 'password' name = 'password' value = {this.state.password} onChange = {this.onChange} />
                    { !!errors.password && <InlineError text={errors.password} /> }
                </Form.Field>
                <Button primary>Log in</Button>
            </Form>
        );
    }
}

export default LoginForm;