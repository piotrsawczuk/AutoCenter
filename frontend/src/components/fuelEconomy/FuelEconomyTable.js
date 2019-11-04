import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import { Table } from 'semantic-ui-react';
import PaginationComponent from '../PaginationComponent';
import FuelEconomy from './FuelEconomy';

class FuelEconomyTable extends Component {
    
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
                        {this.props.fuelEconomyList && this.props.fuelEconomyList.content && this.props.fuelEconomyList.content.map(fuelEconomy => (<FuelEconomy key = {fuelEconomy.id} fuelEconomy = {fuelEconomy} page = {this.props.fuelEconomyList.number}/>))}
                    </Table.Body>
                </Table>
                <Link className="ui large primary left floated button" to={`/cars/${this.props.carId}/fuelEconomy/add`}>Add fuel consumption report</Link>
                <div style = {{float:'right'}}>
                    {this.props.fuelEconomyList && <PaginationComponent pagination = {this.props.fuelEconomyList} onPageChange = {this.props.onPageChange}/>}
                </div>
            </div>
        );
    }
}

export default FuelEconomyTable;