import React, { Component } from 'react';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';

class PasswordChangeForm extends Component {
    
    state = {
        currentPassword: '',
        password: '',
        passwordConfirm: '',
        errors: {}
    };

    validate = (data) => {
        const errors = {};
        if (data.currentPassword.trim() === '') errors.currentPassword = 'Current password cannot be empty';
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
            currentPassword: this.state.currentPassword,
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
                    <label style={{textAlign: 'left'}}>Old password</label>
                    <input placeholder = 'Old password' type = 'password' name = 'currentPassword' value = {this.state.currentPassword} onChange = {this.onChange} />
                    { !!errors.currentPassword && <InlineError text={errors.currentPassword} /> }
                </Form.Field>
                <Form.Field>
                    <label style={{textAlign: 'left'}}>New password</label>
                    <input placeholder = 'New password' type = 'password' name = 'password' value = {this.state.password} onChange = {this.onChange} />
                    { !!errors.password && <InlineError text={errors.password} /> }
                </Form.Field>
                <Form.Field>
                    <label style={{textAlign: 'left'}}>New password confirm</label>
                    <input placeholder = 'New password confirm' type = 'password' name = 'passwordConfirm' value = {this.state.passwordConfirm} onChange = {this.onChange} />
                    { !!errors.passwordConfirm && <InlineError text={errors.passwordConfirm} /> }
                </Form.Field>
                <Button floated={'left'} primary>Change password</Button>
            </Form>
        );
    }
}

export default PasswordChangeForm;