import React, { Component } from 'react';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';

class CarDetailForm extends Component {

    state = {
        vin: '',
        licencePlateNumber: '',
        color: '',
        imageUrl: '',
        errors: {}
    };

    componentWillReceiveProps = ({ carDetail = {} }) => {
        if (carDetail) {
            if (!!Object.keys(carDetail).length) {
                this.setState({...carDetail});
            }
        }
    }

    validate = (data) => {
        const errors = {};
        if (data.vin
            && data.vin.trim() !== ''
            && !data.vin.match(/^[a-zA-Z0-9]{17}$/)) {
                errors.vin = 'VIN is not valid. VIN must be 17 characters long and contain only digits and letters';
            }
        if (data.licencePlateNumber
            && data.licencePlateNumber.trim() !== ''
            && !data.licencePlateNumber.match(/^[a-zA-Z0-9 ]*$/)) {
                errors.licencePlateNumber = 'Licence plate number is not valid';
            } 
        this.setState({ errors });
        return Object.keys(errors).length === 0;
    }

    onSubmit = (e) => {
        const data = {
            vin: this.state.vin,
            licencePlateNumber: this.state.licencePlateNumber,
            color: this.state.color,
            imageUrl: this.state.imageUrl
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
                    <label>VIN</label>
                    <input placeholder = 'VIN' name='vin' value = {this.state.vin} onChange = {this.onChange} />
                    { !!errors.vin && <InlineError text={errors.vin} /> }
                </Form.Field>
                <Form.Field>
                    <label>Licence plate number</label>
                    <input placeholder = 'Licence plate number' name='licencePlateNumber' value = {this.state.licencePlateNumber} onChange = {this.onChange} />
                    { !!errors.licencePlateNumber && <InlineError text={errors.licencePlateNumber} /> }
                </Form.Field>
                <Form.Field>
                    <label>Color</label>
                    <input placeholder = 'Color' name = 'color' value = {this.state.color} onChange = {this.onChange} />
                </Form.Field>
                <Form.Field>
                    <label>Car image URL</label>
                    <input placeholder = 'Car image URL' name = 'imageUrl' value = {this.state.imageUrl} onChange = {this.onChange} />
                </Form.Field>
                <Button size={'large'} floated={'left'} primary>{this.props.carDetail ? 'Edit details' : 'Add details'}</Button>
            </Form>
        );
    }
}

export default CarDetailForm;