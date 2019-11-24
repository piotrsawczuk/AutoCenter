import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import { Table } from 'semantic-ui-react';
import PaginationComponent from '../PaginationComponent';
import FuelConsumption from './FuelConsumption';

class FuelConsumptionTable extends Component {
    
    render() {
        return (
            <div>
                <Table>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell>Date</Table.HeaderCell>
                            <Table.HeaderCell>Fuel type</Table.HeaderCell>
                            <Table.HeaderCell>Driving type</Table.HeaderCell>
                            <Table.HeaderCell>Distance driven [km]</Table.HeaderCell>
                            <Table.HeaderCell>Fuel amount filled [l]</Table.HeaderCell>
                            <Table.HeaderCell>Consumption [l/100 km]</Table.HeaderCell>
                            <Table.HeaderCell>Price per litre</Table.HeaderCell>
                            <Table.HeaderCell>Fill up cost</Table.HeaderCell>
                            <Table.HeaderCell></Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {this.props.fuelConsumptionList && this.props.fuelConsumptionList.content && this.props.fuelConsumptionList.content.map(fuelConsumption => (<FuelConsumption key = {fuelConsumption.id} fuelConsumption = {fuelConsumption} page = {this.props.fuelConsumptionList.number}/>))}
                    </Table.Body>
                </Table>
                <Link className="ui large primary left floated button" to={`/cars/${this.props.carId}/fuelConsumption/add`}>Add fuel consumption report</Link>
                <div style = {{float:'right'}}>
                    {this.props.fuelConsumptionList && <PaginationComponent pagination = {this.props.fuelConsumptionList} onPageChange = {this.props.onPageChange}/>}
                </div>
            </div>
        );
    }
}

export default FuelConsumptionTable;