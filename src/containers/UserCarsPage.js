import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { findAll as findAllCars } from '../actions/userCars';
import UserCarsGrid from '../components/car/UserCarsGrid';

class UserCarsPage extends Component {
    componentDidMount = () => {
        this.props.findAllCars();
    }

    handlePaginationChange = (e, page) => {
        this.props.findAllCars(page.activePage);
    }

    render() {
        return (
            this.props.isAuthenticated ?
                <div>
                    <UserCarsGrid userCars = {this.props.userCars} onPageChange = {this.handlePaginationChange}/>
                </div>
            :
                <div>
                    <Redirect to='/'/>
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

export default connect(mapStateToProps, {findAllCars}) (UserCarsPage);