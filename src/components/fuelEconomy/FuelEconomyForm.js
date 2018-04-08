import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';
import FuelTypeSelection from './FuelTypeSelection';
import DrivingTypeSelection from './DrivingTypeSelection';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';
import '../../assets/styles/datepicker.css';
import { findAll as findAllDrivingTypes} from '../../actions/drivingTypes';
import { findAll as findAllFuelTypes} from '../../actions/fuelTypes';

class FuelEconomyForm extends Component {
    componentDidMount = () => {
        this.props.findAllDrivingTypes();
        this.props.findAllFuelTypes();
    }

    state = {
        fuelType: '',
        drivingType: '',
        date: moment().format('YYYY-MM-DD'),
        fieldDate: moment(),
        distanceDriven : '',
        fuelAmountFilled : '',
        pricePerLitre : '',
        errors : {}
    };
    
    validate = (data) => {
        const errors = {};
        if (data.fuelType.trim() === '') errors.fuelType = 'Choose fuel type';
            else if (!data.fuelType.match(/^[+]?\d+$/)) errors.fuelType = 'Error occured';
        if (data.drivingType.trim() === '') errors.drivingType = 'Choose driving type';
            else if (!data.drivingType.match(/^[+]?\d+$/)) errors.drivingType = 'Error occured';
        if (data.date.trim() === '') errors.date = 'Select date';
        if (data.distanceDriven.trim() === '') errors.distanceDriven = 'Distance driven cannot be empty';
            else if (!data.distanceDriven.match(/^[+]?\d+(\.\d+)?$/)) errors.distanceDriven = 'Distance driven must be number above 0';
        if (data.fuelAmountFilled.trim() === '') errors.fuelAmountFilled = 'Fuel amount filled cannot be empty';
            else if (!data.fuelAmountFilled.match(/^[+]?\d+(\.\d+)?$/)) errors.fuelAmountFilled = 'Fuel amount filled must be number above 0';
        if (data.pricePerLitre.trim() !== '' && !data.pricePerLitre.match(/^[+]?\d+(\.\d+)?$/)) errors.pricePerLitre = 'Price per litre must be number above 0';
        this.setState({errors});
        return Object.keys(errors).length === 0;
    }

    onSubmit = (e) => {
        const data = {
            fuelType: this.state.fuelType,
            drivingType: this.state.drivingType,
            date: this.state.date,
            distanceDriven: this.state.distanceDriven,
            fuelAmountFilled: this.state.fuelAmountFilled,
            pricePerLitre: this.state.pricePerLitre
        };
        const isValid = this.validate(data);
        if (isValid === true) this.props.onSubmit(data);
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    onFuelTypeSelection = (e, fuelType) => {
        this.setState({ fuelType: String(fuelType.value) });
    }

    onDrivingTypeSelection = (e, drivingType) => {
        this.setState({ drivingType: String(drivingType.value) });
    }

    onDateChange = (date) => {
        if (date) {
            this.setState(
                {
                    date: date.format('YYYY-MM-DD'),
                    fieldDate: date
                }
            );
        } else {
            this.setState(
                {
                    fieldDate: date,
                    date: ''
                }
            );
        }
    }

    render() {
        const { errors } = this.state;

        return (
            <Form onSubmit = {this.onSubmit} >
                <Form.Field>
                    <label>Fuel type</label>
                    <FuelTypeSelection fuelTypes = {this.props.fuelTypes} onChange = {this.onFuelTypeSelection}/>
                    { !!errors.fuelType && <InlineError text={errors.fuelType} /> }
                </Form.Field>
                <Form.Field>
                    <label>Driving type</label>
                    <DrivingTypeSelection drivingTypes = {this.props.drivingTypes} onChange = {this.onDrivingTypeSelection}/>
                    { !!errors.drivingType && <InlineError text={errors.drivingType} /> }
                </Form.Field>
                <Form.Field>
                    <label>Date</label>
                    <DatePicker selected={this.state.fieldDate} onChange={this.onDateChange.bind(this)}/>
                    { !!errors.date && <InlineError text={errors.date} /> }
                </Form.Field>
                <Form.Field>
                    <label>Distance driven [km]</label>
                    <input placeholder = 'Distance driven' name='distanceDriven' value = {this.state.distanceDriven} onChange = {this.onChange} />
                    { !!errors.distanceDriven && <InlineError text={errors.distanceDriven} /> }
                </Form.Field>
                <Form.Field>
                    <label>Fuel amount filled [l]</label>
                    <input placeholder = 'Fuel amount filled' name='fuelAmountFilled' value = {this.state.fuelAmountFilled} onChange = {this.onChange} />
                    { !!errors.fuelAmountFilled && <InlineError text={errors.fuelAmountFilled} /> }
                </Form.Field>
                <Form.Field>
                    <label>Price per litre</label>
                    <input placeholder = 'Price per litre' name='pricePerLitre' value = {this.state.pricePerLitre} onChange = {this.onChange} />
                    { !!errors.pricePerLitre && <InlineError text={errors.pricePerLitre} /> }
                </Form.Field>
                <Button size={'large'} floated={'left'} primary >Add report</Button>
            </Form>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        fuelTypes : state.fuelTypesReducer.fuelTypes,
        drivingTypes : state.drivingTypesReducer.drivingTypes
    }
}

export default connect(mapStateToProps,
    {
        findAllDrivingTypes,
        findAllFuelTypes
    }
) (FuelEconomyForm);