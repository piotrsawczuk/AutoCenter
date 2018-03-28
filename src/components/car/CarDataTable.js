import React, { Component } from 'react';
import { Table } from 'semantic-ui-react';

class CarValueTable extends Component {

    checkIfValueExists = (value) => {
        if (value)
            return value;
        else
            return '-';
    }

    render() {
        return (
            <Table color = {'blue'} key = {'blue'}>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell colSpan='2'>{`${this.props.trim.model_year} ${this.props.trim.make_display} ${this.props.trim.model_name} ${this.props.trim.model_trim}`}</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    <Table.Row>
                        <Table.Cell>Make</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.make_display)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Model</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_name)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Trim</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_trim)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Country of Origin</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.make_country)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Year</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_year)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Body</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_body)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Engine Position</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_position)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Engine Type</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_type)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Engine Cylinders</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_cyl)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Engine Displacement (cc)</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_cc)}</Table.Cell>
                    </Table.Row>
                </Table.Body>
            </Table>
        );
        //TODO show more data
    }
}

export default CarValueTable;