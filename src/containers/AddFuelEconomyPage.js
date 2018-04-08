import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import FuelEconomyForm from '../components/fuelEconomy/FuelEconomyForm';
import { save as addFuelEconomy } from '../actions/fuelEconomy';

class AddFuelEconomyPage extends Component {

    onSubmit = (data) => {
        this.props.addFuelEconomy(this.props.match.params.carId, data);
        this.props.history.push(`/cars/${this.props.match.params.carId}/fuelEconomy`);
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
                            <Breadcrumb.Section><Link to={`/cars/${this.props.match.params.carId}/fuelEconomy`}>Back to fuel economy</Link></Breadcrumb.Section>
                            <Breadcrumb.Divider icon='right chevron' />
                            <Breadcrumb.Section active>Add fuel consumption report</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    <div>
                        <FuelEconomyForm onSubmit = {this.onSubmit.bind(this)} />
                    </div>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        fuelEconomy: state.userCarsReducer.fuelEconomy
    }
}

export default connect(mapStateToProps,
    {
        addFuelEconomy
    }
) (AddFuelEconomyPage);