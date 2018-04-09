import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import UserCarsGrid from '../components/car/UserCarsGrid';
import { findAll as findAllCars } from '../actions/userCars';

class UserCarsPage extends Component {
    
    componentDidMount = () => {
        this.props.findAllCars();
    }

    shouldComponentUpdate = (nextProps, nextState) => {
        if (nextProps.userCars !== this.props.userCars)
            return true;
        else
            return false;
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
                    <UserCarsGrid userCars = {this.props.userCars} onPageChange = {this.handlePaginationChange}/>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        userCars: state.userCarsReducer.userCars
    }
}

export default connect(mapStateToProps, { findAllCars }) (UserCarsPage);