import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Message, Breadcrumb } from 'semantic-ui-react';
import CarDetailsForm from '../components/car/CarDetailsForm';
import { findOne as getCar } from '../services/CarsService';
import { findOne as getCarDetails, edit as editCarDetails, save as addCarDetails } from '../services/CarDetailsService';

class EditCarDetailsPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            carDetails: {},
            car: {},
            error: {},
            carDetailsChanged: false
        }
    }

    componentWillMount = async () => {
        try {
            const carId = this.props.match.params.carId;
            const carDetails = await getCarDetails(carId);
            const car = await getCar(carId);
            this.setState({car, carDetails});
        } catch (error) {
            this.setState({error});
        }
    }

    onSubmit = async (data) => {
        try {
            const carId = this.props.match.params.carId;
            const editedCar =  await editCarDetails(carId, data);
            this.setState({carDetailsChanged: editedCar ? true : false });
        } catch (error) {
            if (error.code === 404) {
                try {
                    const carId = this.props.match.params.carId;
                    const editedCar = await addCarDetails(carId, data);
                    this.setState({carDetailsChanged: editedCar ? true : false });
                } catch (error) {
                    this.setState({error});
                }
            } else
                this.setState({error});
        }
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
                                <CarDetailsForm carDetails = {this.state.carDetails} onSubmit = {this.onSubmit} />
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

export default connect(mapStateToProps) (EditCarDetailsPage);