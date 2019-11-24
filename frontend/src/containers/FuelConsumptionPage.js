import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import FuelConsumptionTable from '../components/fuelConsumption/FuelConsumptionTable';
import FuelConsumptionAvgsTable from '../components/fuelConsumption/FuelConsumptionAvgsTable';
import { findAll as findFuelConsumptionList, findAllAvgs as findFuelConsumptionAvgs } from '../actions/fuelConsumption';
import { findOne as findUserCar } from '../actions/cars';

class FuelConsumptionPage extends Component {
    
    componentDidMount = () => {
        this.props.findUserCar(this.props.match.params.carId);
        this.props.findFuelConsumptionAvgs(this.props.match.params.carId);
        this.props.findFuelConsumptionList(this.props.match.params.carId);
    }

    handlePaginationChange = (e, page) => {
        this.props.findFuelConsumptionList(this.props.match.params.carId, page.activePage);
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
                            <Breadcrumb.Section><Link to="/cars">Back to cars</Link></Breadcrumb.Section>
                            <Breadcrumb.Divider icon='right chevron' />
                            <Breadcrumb.Section active>{this.props.car.carName}</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    <h3>Fuel consumption average</h3>
                    <FuelConsumptionAvgsTable fuelConsumptionAvgs = {this.props.fuelConsumptionAvgs} />
                    <h3>Fuel consumption reports</h3>
                    <FuelConsumptionTable fuelConsumptionList = {this.props.fuelConsumptionList} carId = {this.props.match.params.carId} onPageChange = {this.handlePaginationChange}/>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        fuelConsumptionList: state.fuelConsumptionReducer.fuelConsumptionList,
        fuelConsumptionAvgs: state.fuelConsumptionReducer.fuelConsumptionAvgs,
        car: state.carsReducer.car
    }
}

export default connect(mapStateToProps, { findFuelConsumptionList, findFuelConsumptionAvgs, findUserCar }) (FuelConsumptionPage);