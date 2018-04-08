import React, { Component } from 'react';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';

class PasswordChangeForm extends Component {
    
    state = {
        email: '',
        errors: {}
    };

    validate = (data) => {
        const errors = {};
        if (data.email.trim() === '') errors.email = 'Email cannot be empty';
            else if (!data.email.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/i)) errors.email = 'Email is not valid';
        this.setState({ errors });
        return Object.keys(errors).length === 0;
    }

    onSubmit = (e) => {
        const data = {
            email: this.state.email
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
                    <label style={{textAlign: 'left'}}>Email</label>
                    <input placeholder = 'Email' name = 'email' value = {this.state.email} onChange = {this.onChange} />
                    { !!errors.email && <InlineError text={errors.email} /> }
                </Form.Field>
                <Button floated={'left'} primary>Change email</Button>
            </Form>
        );
    }
}

export default PasswordChangeForm;