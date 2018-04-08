import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Table } from 'semantic-ui-react';
import { findAll as findAllDrivingTypes} from '../../actions/drivingTypes';
import { findAll as findAllFuelTypes} from '../../actions/fuelTypes';

class FuelEconomyAvgsTable extends Component {

    componentDidMount = () => {
        this.props.findAllDrivingTypes();
        this.props.findAllFuelTypes();
    }

    getFuelEconomyAvgValue = (avgsCollection, drivingTypeValue, fuelTypeValue) => {
        if (!avgsCollection || avgsCollection.length <= 0) 
            return '-';
        for (var i=0; i < avgsCollection.length; i++) {
            if (avgsCollection[i].drivingTypeValue === drivingTypeValue && avgsCollection[i].fuelTypeValue === fuelTypeValue) {
                return avgsCollection[i].economyAverage;
            }
        }
        return '-';
    }

    render() {
        if (this.props.fuelTypes
            && this.props.fuelTypes.length > 0
            && this.props.drivingTypes
            && this.props.drivingTypes.length > 0
        ) {
            return (
                <Table celled compact definition>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell />
                            <Table.HeaderCell>{this.props.drivingTypes[0].drivingType}</Table.HeaderCell>
                            <Table.HeaderCell>{this.props.drivingTypes[1].drivingType}</Table.HeaderCell>
                            <Table.HeaderCell>{this.props.drivingTypes[2].drivingType}</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        <Table.Row>
                            <Table.Cell collapsing>{this.props.fuelTypes[0].fuelType}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[0].value, this.props.fuelTypes[0].value)}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[1].value, this.props.fuelTypes[0].value)}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[2].value, this.props.fuelTypes[0].value)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell collapsing>{this.props.fuelTypes[1].fuelType}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[0].value, this.props.fuelTypes[1].value)}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[1].value, this.props.fuelTypes[1].value)}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[2].value, this.props.fuelTypes[1].value)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell collapsing>{this.props.fuelTypes[2].fuelType}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[0].value, this.props.fuelTypes[2].value)}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[1].value, this.props.fuelTypes[2].value)}</Table.Cell>
                            <Table.Cell>{this.getFuelEconomyAvgValue(this.props.fuelEconomyAvgs, this.props.drivingTypes[2].value, this.props.fuelTypes[2].value)}</Table.Cell>
                        </Table.Row>
                    </Table.Body>
                </Table>
            );
        } else
            return (<div style = {{ display: 'none' }}></div>);

    }
}

const mapStateToProps = (state) => {
    return {
        fuelTypes : state.fuelTypesReducer.fuelTypes,
        drivingTypes : state.drivingTypesReducer.drivingTypes
    }
}

export default connect(mapStateToProps,
    {
        findAllDrivingTypes,
        findAllFuelTypes
    }
) (FuelEconomyAvgsTable);