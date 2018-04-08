import React, { Component } from 'react';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';

class UserDetailsForm extends Component {

    state = {
        firstname: '',
        surname: '',
        address: '',
        city: '',
        zipCode: '',
        phoneNumber: '',
        errors: {}
    };

    componentDidMount = () => {
        if (this.props.userDetails) {
            const userDetails = this.props.userDetails;
            this.setState(
                {
                    firstname: userDetails.firstname ? userDetails.firstname : '',
                    surname : userDetails.surname ? userDetails.surname : '',
                    address : userDetails.address ? userDetails.address : '',
                    city : userDetails.city ? userDetails.city : '',
                    zipCode : userDetails.zipCode ? userDetails.zipCode : '',
                    phoneNumber : userDetails.phoneNumber ? userDetails.phoneNumber : '',
                }
            );
        }
    }
    
    validate = (data) => {
        const errors = {};
        if (data.zipCode.trim() !== '' && !data.zipCode.match(/^([a-zA-Z0-9 -]+)?$/)) errors.zipCode = 'Zip code is incorrect';
        if (data.phoneNumber.trim() !== '' && !data.phoneNumber.match(/^([+]?[0-9 -]+)?$/)) errors.phoneNumber = 'Phone number is incorrect';
        this.setState({errors});
        return Object.keys(errors).length === 0;
    }

    onSubmit = (e) => {
        const data = {
            firstname: this.state.firstname,
            surname: this.state.surname,
            address: this.state.address,
            city: this.state.city,
            zipCode: this.state.zipCode,
            phoneNumber: this.state.phoneNumber
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
                    <label>First name</label>
                    <input placeholder = 'First name' name='firstname' value = {this.state.firstname} onChange = {this.onChange} />
                </Form.Field>
                <Form.Field>
                    <label>Surname</label>
                    <input placeholder = 'Surname' name='surname' value = {this.state.surname} onChange = {this.onChange} />
                </Form.Field>
                <Form.Field>
                    <label>Address</label>
                    <input placeholder = 'Address' name='address' value = {this.state.address} onChange = {this.onChange} />
                </Form.Field>
                <Form.Field>
                    <label>City</label>
                    <input placeholder = 'City' name='city' value = {this.state.city} onChange = {this.onChange} />
                </Form.Field>
                <Form.Field>
                    <label>Zip code</label>
                    <input placeholder = 'Zip code' name='zipCode' value = {this.state.zipCode} onChange = {this.onChange} />
                    { !!errors.zipCode && <InlineError text={errors.zipCode} /> }
                </Form.Field>
                <Form.Field>
                    <label>Phone number</label>
                    <input placeholder = 'Phone number' name='phoneNumber' value = {this.state.phoneNumber} onChange = {this.onChange} />
                    { !!errors.phoneNumber && <InlineError text={errors.phoneNumber} /> }
                </Form.Field>
                <Button size={'large'} floated={'left'} primary >Edit account details</Button>
            </Form>
        );
    }
}

export default UserDetailsForm;