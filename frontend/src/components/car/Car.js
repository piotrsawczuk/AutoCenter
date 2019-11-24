import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Button, Grid, Image, Table } from 'semantic-ui-react';
import { deleteCar } from '../../actions/cars';
import { findOne as findTrim } from '../../actions/trim';
import CarDataTable from '../car/CarDataTable';

class Car extends Component {

    state = {
        visibleDataTable: false
    }

    deleteCar = () => {
        this.props.deleteCar(this.props.car.id, this.props.page);
    }

    showCarData = () => {
        if (this.state.visibleDataTable) {
            this.setState({ visibleDataTable: false });
        } else {
            this.props.findTrim(this.props.car.carApiId);
            this.setState({ visibleDataTable: true });
        }
    }

    render () {
        const { car, trim } = this.props;
        return (
            <Grid.Row columns={3}>
                <Grid.Column width={4}>
                    <Image style={{borderRadius: '4px'}} className='ui medium image' src={(car.carDetail && car.carDetail.imageUrl) || require('../../assets/images/image.png')}/>
                </Grid.Column>
                <Grid.Column width={8}>
                    <Table color = {'blue'} key = {'blue'}>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell colSpan='2'>{car.carName}</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        <Table.Body>
                            <Table.Row>
                                <Table.Cell>VIN</Table.Cell>
                                <Table.Cell textAlign='right'>{(car.carDetail && car.carDetail.vin) || '-'}</Table.Cell>
                            </Table.Row>
                            <Table.Row>
                                <Table.Cell>Licence plate number</Table.Cell>
                                <Table.Cell textAlign='right'>{(car.carDetail && car.carDetail.licencePlateNumber) || '-'}</Table.Cell>
                            </Table.Row>
                            <Table.Row>
                                <Table.Cell>Color</Table.Cell>
                                <Table.Cell textAlign='right'>{(car.carDetail && car.carDetail.color) || '-'}</Table.Cell>
                            </Table.Row>
                        </Table.Body>
                    </Table>
                </Grid.Column>
                <Grid.Column width={4}>
                    <Button.Group vertical compact floated={'right'}>
                        <Button className="ui large primary left floated button" onClick={this.showCarData}>{this.state.visibleDataTable && trim && trim.model_id === car.carApiId ? 'Hide car data' : 'Show car data'}</Button>
                        <Link className="ui large primary left floated button" to={`/cars/${car.id}/fuelConsumption`}>Fuel consumption</Link>
                        <Link className="ui large primary left floated button" to={`/cars/${car.id}/repairs`}>Repairs</Link>
                        <Link className="ui large primary left floated button" to={`/cars/${car.id}/edit`}>Edit car</Link>
                        <Button className="ui large primary left floated button" onClick={this.deleteCar}>Delete car</Button>
                    </Button.Group>
                </Grid.Column>
                {this.state.visibleDataTable && trim && trim.model_id === car.carApiId && <CarDataTable trim = {trim}/>}
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