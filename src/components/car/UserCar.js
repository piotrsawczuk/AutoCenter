import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Button, Grid, Image, Table } from 'semantic-ui-react';
import axios from 'axios';
import { deleteCar } from '../../actions/userCars';
import { findOne as findTrim } from '../../actions/trim';
import CarDataTable from '../car/CarDataTable';

class UserCar extends Component {
    state = 
    { 
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
        this.props.deleteCar(this.props.userCar.id);
        this.setState(
            { 
                isDeleted: true
            }
        );
    }

    showCarData = () => {
        if (this.state.visibleDataTable) {
            this.setState(
                { 
                    visibleDataTable: false
                }
            );
        } else {
            this.props.findTrim(this.props.userCar.carApiId);
            this.setState(
                { 
                    visibleDataTable: true
                }
            );
        }
    }


    render () {
        return (
            !this.state.isDeleted ?
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
                                {
                                    this.state.userCarDetails && this.state.userCarDetails.vin &&
                                    <Table.Row>
                                        <Table.Cell>VIN</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.userCarDetails.vin}</Table.Cell>
                                    </Table.Row>
                                }
                                {
                                    this.state.userCarDetails && this.state.userCarDetails.licencePlateNumber &&
                                    <Table.Row>
                                        <Table.Cell>Model</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.userCarDetails.licencePlateNumber}</Table.Cell>
                                    </Table.Row>
                                }
                                {
                                    this.state.userCarDetails && this.state.userCarDetails.color &&
                                    <Table.Row>
                                        <Table.Cell>Trim</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.userCarDetails.color}</Table.Cell>
                                    </Table.Row>
                                }
                            </Table.Body>
                        </Table>
                    </Grid.Column>
                    <Grid.Column width={3}>
                        <Button.Group primary vertical={true} size='mini'  floated={'right'} compact={false}>
                            <Button onClick={() => this.showCarData()}>{this.state.visibleDataTable && this.props.trim && this.props.trim.model_id === this.props.userCar.carApiId ? 'Hide car data' : 'Show car data'}</Button>
                            <Button>Fuel economy</Button>
                            <Button>Repairs</Button>
                            <Button>Edit car</Button>
                            <Button onClick={() => this.deleteCar()}>Delete car</Button>
                        </Button.Group>
                    </Grid.Column>
                    {this.state.visibleDataTable && this.props.trim && this.props.trim.model_id === this.props.userCar.carApiId && <CarDataTable trim = {this.props.trim}/>}
                </Grid.Row>
            :
                <div>
                    <Redirect to='/cars'/>
                </div>
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