import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Message, Breadcrumb } from 'semantic-ui-react';
import UserCarDetailsForm from '../components/car/UserCarDetailsForm';
import { findOne as getCar } from '../services/UserCarsService';
import { findOne as getCarDetails, edit as editCarDetails, save as addCarDetails } from '../services/UserCarDetailsService';

class EditUserCarDetailsPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            carDetails: {},
            car: {},
            error: {},
            carDetailsChanged: false
        }
    }

    componentWillMount = () => {
        const carId = this.props.match.params.carId;
        getCarDetails(carId)
            .then(carDetails => this.setState({carDetails}))
            .catch(error => this.setState({error}));
        getCar(carId)
            .then(car => this.setState({car}))
            .catch(error => this.setState({error}));
    }

    onSubmit = (data) => {
        const carId = this.props.match.params.carId;
        editCarDetails(carId, data)
            .then(editedCar => {
                if (editedCar) this.setState({carDetailsChanged: true})
            })
            .catch(error => {
                if (error.code === 404) {
                    addCarDetails(carId, data)
                    .then(editedCar => {
                        if (editedCar) this.setState({carDetailsChanged: true})
                    })
                    .catch(error => {
                        this.setState({error})
                    });
                } else
                    this.setState({error})
            });
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
                :
                    this.state.carDetailsChanged ?
                        <div>
                            <Redirect to='/cars'/>
                        </div>
                    :
                        <div>
                            <div style={{marginBottom: '30px'}}>
                                <Breadcrumb size='big'>
                                    <Breadcrumb.Section><Link to='/cars'>Back to cars</Link></Breadcrumb.Section>
                                    <Breadcrumb.Divider icon='right chevron' />
                                    <Breadcrumb.Section active>{this.state.car.carName}</Breadcrumb.Section>
                                </Breadcrumb>
                            </div>
                            <div style={{marginBottom: '65px'}}>
                                <UserCarDetailsForm userCarDetails = {this.state.carDetails} onSubmit = {this.onSubmit.bind(this)} />
                            </div>
                            <div>
                                {this.state.error.message && <Message error header="Error!" content={this.state.error.message}/> }
                            </div>
                        </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps) (EditUserCarDetailsPage);