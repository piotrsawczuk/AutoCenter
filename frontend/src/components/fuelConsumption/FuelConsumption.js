import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Button, Table } from 'semantic-ui-react';
import { deleteFuelConsumption } from '../../actions/fuelConsumption';

class FuelConsumption extends Component {
    
    state = { 
        isDeleted: false
    }
    
    deleteReport = () => {
        this.props.deleteFuelConsumption(this.props.car.id, this.props.fuelConsumption.id, this.props.page);
        this.setState({ isDeleted: true });
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
                <Table.Row>
                    <Redirect to={`/cars/${this.props.car.id}/fuelConsumption`}/>
                </Table.Row>
            :
                <Table.Row>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.date)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.fuelType.fuelType)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.drivingType.drivingType)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.distanceDriven)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.fuelAmountFilled)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.consumption)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.pricePerLitre)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelConsumption.fillUpCost)}</Table.Cell>
                    <Table.Cell>
                        <Button.Group primary vertical={true} size='mini'  floated={'right'} compact={false}>
                            {/* <Button>Edit report</Button> */}
                            <Button onClick={() => this.deleteReport()}>Delete report</Button>
                        </Button.Group>
                    </Table.Cell>
                </Table.Row>
        );
    }
};

const mapStateToProps = (state) => {
    return {
        fuelConsumptionList: state.fuelConsumptionReducer.fuelConsumptionList,
        car: state.carsReducer.car
    }
}

export default connect(mapStateToProps, { deleteFuelConsumption }) (FuelConsumption);