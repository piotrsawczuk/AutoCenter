import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import { Table } from 'semantic-ui-react';
import PaginationComponent from '../PaginationComponent';
import Repair from './Repair';

class RepairsTable extends Component {
    render() {
        return (
            <div>
                <Table>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell>Date</Table.HeaderCell>
                            <Table.HeaderCell>Exploitation type</Table.HeaderCell>
                            <Table.HeaderCell>Mileage [km]</Table.HeaderCell>
                            <Table.HeaderCell>Cost</Table.HeaderCell>
                            <Table.HeaderCell>Description</Table.HeaderCell>
                            <Table.HeaderCell></Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {this.props.repairs && this.props.repairs.content && this.props.repairs.content.map(repair => (<Repair key = {repair.id} repair = {repair} />))}
                    </Table.Body>
                </Table>
                <Link className="ui large primary left floated button" to={`/cars/${this.props.carId}/addRepair`}>Add repair</Link>
                <div style = {{float:'right'}}>
                    {this.props.repairs && <PaginationComponent pagination = {this.props.repairs} onPageChange = {this.props.onPageChange}/>}
                </div>
            </div>
        );
    }
}

export default RepairsTable;