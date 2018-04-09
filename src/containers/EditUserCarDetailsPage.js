import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import UserCarDetailsForm from '../components/car/UserCarDetailsForm';
import { edit as editUserCarDetails } from '../actions/userCarDetails';
import { findOne as findUserCar } from '../actions/userCars';

class EditUserCarDetailsPage extends Component {

    componentDidMount = () => {
        this.props.findUserCar(this.props.match.params.carId);
    }

    onSubmit = (data) => {
        this.props.editUserCarDetails(this.props.match.params.carId, data);
        this.props.history.push('/cars');
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
                            <Breadcrumb.Section><Link to='/cars'>Back to cars</Link></Breadcrumb.Section>
                            <Breadcrumb.Divider icon='right chevron' />
                            <Breadcrumb.Section active>{this.props.userCar.carName}</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    {/* {this.props.userCar && <UserCarDetailsForm userCarDetails = {this.props.userCar.carDetail} onSubmit = {this.onSubmit.bind(this)} />} */}
                    <UserCarDetailsForm onSubmit = {this.onSubmit.bind(this)} />
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        userCar: state.userCarsReducer.userCar,
    }
}

export default connect(mapStateToProps, { editUserCarDetails, findUserCar }) (EditUserCarDetailsPage);