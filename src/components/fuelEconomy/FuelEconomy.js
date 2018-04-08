import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Button, Table } from 'semantic-ui-react';
import { deleteFuelEconomy } from '../../actions/fuelEconomy';

class FuelEconomy extends Component {
    
    state = { 
        isDeleted: false
    }
    
    deleteReport = () => {
        this.props.deleteFuelEconomy(this.props.userCar.id, this.props.fuelEconomy.id);
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
                    <Redirect to={`/cars/${this.props.userCar.id}/fuelEconomy`}/>
                </Table.Row>
            :
                <Table.Row>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.date)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.fuelType.fuelType)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.drivingType.drivingType)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.distanceDriven)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.fuelAmountFilled)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.consumption)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.pricePerLitre)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.fuelEconomy.fillUpCost)}</Table.Cell>
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
        fuelEconomyList: state.fuelEconomyReducer.fuelEconomyList,
        userCar: state.userCarsReducer.userCar
    }
}

export default connect(mapStateToProps, { deleteFuelEconomy }) (FuelEconomy);