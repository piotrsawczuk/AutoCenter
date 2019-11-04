import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import CarsGrid from '../components/car/CarsGrid';
import { findAll as findAllCars } from '../actions/cars';

class CarsPage extends Component {
    
    componentDidMount = () => {
        this.props.findAllCars();
    }

    handlePaginationChange = (e, page) => {
        this.props.findAllCars(page.activePage);
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
            :
                <div>
                    <CarsGrid cars = {this.props.cars} onPageChange = {this.handlePaginationChange}/>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        cars: state.carsReducer.cars
    }
}

export default connect(mapStateToProps, { findAllCars }) (CarsPage);