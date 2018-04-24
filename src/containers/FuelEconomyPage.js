import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import FuelEconomyTable from '../components/fuelEconomy/FuelEconomyTable';
import FuelEconomyAvgsTable from '../components/fuelEconomy/FuelEconomyAvgsTable';
import { findAll as findFuelEconomyList, findAllAvgs as findFuelEconomyAvgs } from '../actions/fuelEconomy';
import { findOne as findUserCar } from '../actions/cars';

class FuelEconomyPage extends Component {
    
    componentDidMount = () => {
        this.props.findUserCar(this.props.match.params.carId);
        this.props.findFuelEconomyAvgs(this.props.match.params.carId);
        this.props.findFuelEconomyList(this.props.match.params.carId);
    }

    handlePaginationChange = (e, page) => {
        this.props.findFuelEconomyList(this.props.match.params.carId, page.activePage);
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
                    <FuelEconomyAvgsTable fuelEconomyAvgs = {this.props.fuelEconomyAvgs} />
                    <h3>Fuel consumption reports</h3>
                    <FuelEconomyTable fuelEconomyList = {this.props.fuelEconomyList} carId = {this.props.match.params.carId} onPageChange = {this.handlePaginationChange}/>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        fuelEconomyList: state.fuelEconomyReducer.fuelEconomyList,
        fuelEconomyAvgs: state.fuelEconomyReducer.fuelEconomyAvgs,
        car: state.carsReducer.car
    }
}

export default connect(mapStateToProps, { findFuelEconomyList, findFuelEconomyAvgs, findUserCar }) (FuelEconomyPage);