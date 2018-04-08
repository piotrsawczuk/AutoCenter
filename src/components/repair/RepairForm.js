import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Form, Button } from 'semantic-ui-react';
import InlineError from '../InlineError';
import ExploitationTypeSelection from './ExploitationTypeSelection';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';
import '../../assets/styles/datepicker.css';
import { findAll as findAllExploitationTypes} from '../../actions/exploitationTypes';

class RepairForm extends Component {
    componentDidMount = () => {
        this.props.findAllExploitationTypes();
    }

    state = {
        exploitationType: '',
        date: moment().format('YYYY-MM-DD'),
        fieldDate: moment(),
        mileage : '',
        cost : '',
        description : '',
        errors : {}
    };
    
    validate = (data) => {
        const errors = {};
        if (data.exploitationType.trim() === '') errors.exploitationType = 'Choose exploitation type';
            else if (!data.exploitationType.match(/^[+]?\d+$/)) errors.exploitationType = 'Error occured';
        if (data.date.trim() === '') errors.date = 'Select date';
        if (data.mileage.trim() === '') errors.mileage = 'Mileage cannot be empty';
            else if (!data.mileage.match(/^[+]?\d+$/)) errors.mileage = 'Mileage must be number above 0';
        if (data.cost.trim() === '') errors.cost = 'Cost cannot be empty';
            else if (!data.cost.match(/^[+]?\d+(\.\d+)?$/)) errors.cost = 'Cost must be number above 0';
        this.setState({errors});
        return Object.keys(errors).length === 0;
    }

    onSubmit = (e) => {
        const data = {
            exploitationType: this.state.exploitationType,
            date: this.state.date,
            mileage: this.state.mileage,
            cost: this.state.cost,
            description: this.state.description
        };
        const isValid = this.validate(data);
        if (isValid === true) this.props.onSubmit(data);
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    onExploitationTypeSelection = (e, exploitationType) => {
        this.setState({ exploitationType: String(exploitationType.value) });
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
                    <label>Exploitation type</label>
                    <ExploitationTypeSelection exploitationTypes = {this.props.exploitationTypes} onChange = {this.onExploitationTypeSelection}/>
                    { !!errors.exploitationType && <InlineError text={errors.exploitationType} /> }
                </Form.Field>
                <Form.Field>
                    <label>Date</label>
                    <DatePicker selected={this.state.fieldDate} onChange={this.onDateChange.bind(this)}/>
                    { !!errors.date && <InlineError text={errors.date} /> }
                </Form.Field>
                <Form.Field>
                    <label>Mileage [km]</label>
                    <input placeholder = 'Mileage' name='mileage' value = {this.state.mileage} onChange = {this.onChange} />
                    { !!errors.mileage && <InlineError text={errors.mileage} /> }
                </Form.Field>
                <Form.Field>
                    <label>Cost</label>
                    <input placeholder = 'Cost' name='cost' value = {this.state.cost} onChange = {this.onChange} />
                    { !!errors.cost && <InlineError text={errors.cost} /> }
                </Form.Field>
                <Form.Field>
                    <label>Description</label>
                    <input placeholder = 'Description' name='description' value = {this.state.description} onChange = {this.onChange} />
                </Form.Field>
                <Button size={'large'} floated={'left'} primary >Add report</Button>
            </Form>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        exploitationTypes : state.exploitationTypesReducer.exploitationTypes
    }
}

export default connect(mapStateToProps,
    {
        findAllExploitationTypes
    }
) (RepairForm);