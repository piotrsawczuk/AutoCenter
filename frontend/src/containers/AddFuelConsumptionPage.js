import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import FuelConsumptionForm from '../components/fuelConsumption/FuelConsumptionForm';
import { save as addFuelConsumption } from '../actions/fuelConsumption';

class AddFuelConsumptionPage extends Component {

    onSubmit = (data) => {
        this.props.addFuelConsumption(this.props.match.params.carId, data);
        this.props.history.push(`/cars/${this.props.match.params.carId}/fuelConsumption`);
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
            :
                <div>
                    <div style={{marginBottom: '30px'}}>
                        <Breadcrumb size='big'>
                            <Breadcrumb.Section><Link to={`/cars/${this.props.match.params.carId}/fuelConsumption`}>Back to fuel consumption</Link></Breadcrumb.Section>
                            <Breadcrumb.Divider icon='right chevron' />
                            <Breadcrumb.Section active>Add fuel consumption report</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    <div>
                        <FuelConsumptionForm onSubmit = {this.onSubmit.bind(this)} />
                    </div>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        fuelConsumption: state.fuelConsumptionReducer.fuelConsumption
    }
}

export default connect(mapStateToProps, { addFuelConsumption }) (AddFuelConsumptionPage);