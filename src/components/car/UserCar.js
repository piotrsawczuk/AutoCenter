import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Button, Grid, Image, Table } from 'semantic-ui-react';
import axios from 'axios';
import { deleteCar } from '../../actions/userCars';
import { findOne as findTrim } from '../../actions/trim';
import CarDataTable from '../car/CarDataTable';

class UserCar extends Component {

    state = { 
        isDeleted: false,
        visibleDataTable: false
    }
    
    componentDidMount = () => {
       this.loadUserCarDetails(this.props.userCar.id);
    }

    loadUserCarDetails = (carId) => {
        const url = 'http://localhost:8080/cars';
        axios.get(`${url}/${carId}/details`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(res => {
            const userCarDetails = res.data;
            this.setState({ userCarDetails });
        });
    }

    deleteCar = () => {
        this.props.deleteCar(this.props.userCar.id, this.props.page);
        this.setState({ isDeleted: true });
    }

    showCarData = () => {
        if (this.state.visibleDataTable) {
            this.setState({ visibleDataTable: false });
        } else {
            this.props.findTrim(this.props.userCar.carApiId);
            this.setState({ visibleDataTable: true });
        }
    }

    checkIfValueExists = (value) => {
        if (value)
            return value;
        else
            return '-';
    }

    render () {
        return (
            this.state.isDeleted ?
                <div>
                    <Redirect to='/cars'/>
                </div>
            :
                this.state.redirectToFuelEconomy ?
                    <div>
                        <Redirect to={`/cars/${this.props.userCar.id}/fuel-economy`}/>
                    </div>
                :
                this.state.redirectToRepairs ?
                    <div>
                        <Redirect to={`/cars/${this.props.userCar.id}/repairs`}/>
                    </div>
                :
                    <Grid.Row columns={3}>
                        <Grid.Column width={4}>
                            {this.state.userCarDetails && this.state.userCarDetails.imageUrl ? 
                                <Image style={{borderRadius: '4px'}} className='ui medium image' src={this.state.userCarDetails.imageUrl}/> 
                            : 
                                <Image style={{borderRadius: '4px'}} className='ui medium image' src={require('../../assets/images/image.png')}/>}
                        </Grid.Column>
                        <Grid.Column width={9}>
                            <Table color = {'blue'} key = {'blue'}>
                                <Table.Header>
                                    <Table.Row>
                                        <Table.HeaderCell colSpan='2'>{this.props.userCar.carName}</Table.HeaderCell>
                                    </Table.Row>
                                </Table.Header>
                                <Table.Body>
                                    <Table.Row>
                                        <Table.Cell>VIN</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.userCarDetails ? this.checkIfValueExists(this.state.userCarDetails.vin) : '-'}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>Licence plate number</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.userCarDetails ? this.checkIfValueExists(this.state.userCarDetails.licencePlateNumber) : '-'}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>Color</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.userCarDetails ? this.checkIfValueExists(this.state.userCarDetails.color) : '-'}</Table.Cell>
                                    </Table.Row>
                                </Table.Body>
                            </Table>
                        </Grid.Column>
                        <Grid.Column width={3}>
                            <Button.Group primary vertical={true} size='mini'  floated={'right'} compact={false}>
                                <Button onClick={() => this.showCarData()}>{this.state.visibleDataTable && this.props.trim && this.props.trim.model_id === this.props.userCar.carApiId ? 'Hide car data' : 'Show car data'}</Button>
                                <Link className="ui large primary left floated button" to={`/cars/${this.props.userCar.id}/fuelEconomy`}>Fuel economy</Link>
                                <Link className="ui large primary left floated button" to={`/cars/${this.props.userCar.id}/repairs`}>Repairs</Link>
                                <Link className="ui large primary left floated button" to={`/cars/${this.props.userCar.id}/edit`}>Edit car</Link>
                                <Button onClick={() => this.deleteCar()}>Delete car</Button>
                            </Button.Group>
                        </Grid.Column>
                        {this.state.visibleDataTable && this.props.trim && this.props.trim.model_id === this.props.userCar.carApiId && <CarDataTable trim = {this.props.trim}/>}
                    </Grid.Row>
        );
    }
};

const mapStateToProps = (state) => {
    return {
        userCars: state.userCarsReducer.userCars,
        trim: state.trimReducer.trim
    }
}

export default connect(mapStateToProps, { deleteCar, findTrim }) (UserCar);