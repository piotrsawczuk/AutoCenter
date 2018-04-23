import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Button, Grid, Image, Table } from 'semantic-ui-react';
import { deleteCar } from '../../actions/cars';
import { findOne as findTrim } from '../../actions/trim';
import { findOne as getCarDetails } from '../../services/CarDetailsService';
import CarDataTable from '../car/CarDataTable';

class Car extends Component {

    state = { 
        isDeleted: false,
        visibleDataTable: false
    }

    componentWillMount = async () => {
        try {
            const carId = this.props.car.id;
            const carDetails = await getCarDetails(carId);
            this.setState({carDetails});
        } catch (error) {
            this.setState({error});
        }
    }

    deleteCar = () => {
        this.props.deleteCar(this.props.car.id, this.props.page);
        this.setState({ isDeleted: true });
    }

    showCarData = () => {
        if (this.state.visibleDataTable) {
            this.setState({ visibleDataTable: false });
        } else {
            this.props.findTrim(this.props.car.carApiId);
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
                        <Redirect to={`/cars/${this.props.car.id}/fuel-economy`}/>
                    </div>
                :
                this.state.redirectToRepairs ?
                    <div>
                        <Redirect to={`/cars/${this.props.car.id}/repairs`}/>
                    </div>
                :
                    <Grid.Row columns={3}>
                        <Grid.Column width={4}>
                            {this.state.carDetails && this.state.carDetails.imageUrl ?
                                <Image style={{borderRadius: '4px'}} className='ui medium image' src={this.state.carDetails.imageUrl}/>
                            : 
                                <Image style={{borderRadius: '4px'}} className='ui medium image' src={require('../../assets/images/image.png')}/>}
                        </Grid.Column>
                        <Grid.Column width={8}>
                            <Table color = {'blue'} key = {'blue'}>
                                <Table.Header>
                                    <Table.Row>
                                        <Table.HeaderCell colSpan='2'>{this.props.car.carName}</Table.HeaderCell>
                                    </Table.Row>
                                </Table.Header>
                                <Table.Body>
                                    <Table.Row>
                                        <Table.Cell>VIN</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.carDetails ? this.checkIfValueExists(this.state.carDetails.vin) : '-'}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>Licence plate number</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.carDetails ? this.checkIfValueExists(this.state.carDetails.licencePlateNumber) : '-'}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>Color</Table.Cell>
                                        <Table.Cell textAlign='right'>{this.state.carDetails ? this.checkIfValueExists(this.state.carDetails.color) : '-'}</Table.Cell>
                                    </Table.Row>
                                </Table.Body>
                            </Table>
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <Button.Group vertical compact floated={'right'}>
                                <Button className="ui large primary left floated button" onClick={() => this.showCarData()}>{this.state.visibleDataTable && this.props.trim && this.props.trim.model_id === this.props.car.carApiId ? 'Hide car data' : 'Show car data'}</Button>
                                <Link className="ui large primary left floated button" to={`/cars/${this.props.car.id}/fuelEconomy`}>Fuel economy</Link>
                                <Link className="ui large primary left floated button" to={`/cars/${this.props.car.id}/repairs`}>Repairs</Link>
                                <Link className="ui large primary left floated button" to={`/cars/${this.props.car.id}/edit`}>Edit car</Link>
                                <Button className="ui large primary left floated button" onClick={() => this.deleteCar()}>Delete car</Button>
                            </Button.Group>
                        </Grid.Column>
                        {this.state.visibleDataTable && this.props.trim && this.props.trim.model_id === this.props.car.carApiId && <CarDataTable trim = {this.props.trim}/>}
                    </Grid.Row>
        );
    }
};

const mapStateToProps = (state) => {
    return {
        trim: state.trimReducer.trim
    }
}

export default connect(mapStateToProps, { deleteCar, findTrim }) (Car);