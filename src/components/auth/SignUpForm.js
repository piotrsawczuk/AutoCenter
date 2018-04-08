import React, { Component } from 'react';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';

class SignUpForm extends Component {
    
    state = {
        username: '',
        email: '',
        password: '',
        passwordConfirm: '',
        errors: {}
    };

    validate = (data) => {
        const errors = {};
        if (data.username.trim() === '') errors.username = 'Username cannot be empty';
            else if (data.username.trim().length < 2) errors.username = 'Username must be at least 2 characters long';
        if (data.email.trim() === '') errors.email = 'Email cannot be empty';
            else if (!data.email.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/i)) errors.email = 'Email is not valid';
        if (data.password.trim() === '') errors.password = 'Password cannot be empty';
            else if (data.password.trim().length < 6) errors.password = 'Password must be at least 6 characters long';
        if (data.passwordConfirm.trim() === '') errors.passwordConfirm = 'Password confirm cannot be empty';
            else if (data.passwordConfirm.trim().length < 6) errors.passwordConfirm = 'Password confirm must be at least 6 characters long';
        if (data.passwordConfirm.trim() !== data.password.trim()) errors.passwordConfirm = 'Password confirm is not same as password';
        this.setState({ errors });
        return Object.keys(errors).length === 0;
    }

    onSubmit = (e) => {
        const data = {
            username: this.state.username,
            email: this.state.email,
            password: this.state.password,
            passwordConfirm: this.state.passwordConfirm
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
            <Form onSubmit = {this.onSubmit} >
                <Form.Field>
                    <label style={{textAlign: 'left'}}>Username</label>
                    <input placeholder = 'Username' name='username' value = {this.state.username} onChange = {this.onChange} />
                    { !!errors.username && <InlineError text={errors.username} /> }
                </Form.Field>
                <Form.Field>
                    <label style={{textAlign: 'left'}}>Email</label>
                    <input placeholder = 'Email' name='email' value = {this.state.email} onChange = {this.onChange} />
                    { !!errors.email && <InlineError text={errors.email} /> }
                </Form.Field>
                <Form.Field>
                    <label style={{textAlign: 'left'}}>Password</label>
                    <input placeholder = 'Password' type = 'password' name = 'password' value = {this.state.password} onChange = {this.onChange} />
                    { !!errors.password && <InlineError text={errors.password} /> }
                </Form.Field>
                <Form.Field>
                    <label style={{textAlign: 'left'}}>Password confirm</label>
                    <input placeholder = 'Password confirm' type = 'password' name = 'passwordConfirm' value = {this.state.passwordConfirm} onChange = {this.onChange} />
                    { !!errors.passwordConfirm && <InlineError text={errors.passwordConfirm} /> }
                </Form.Field>
                <Button primary>Sign up</Button>
            </Form>
        );
    }
}

export default SignUpForm;