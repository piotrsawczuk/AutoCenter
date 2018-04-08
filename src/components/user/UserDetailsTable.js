import React, { Component } from 'react';
import { Table } from 'semantic-ui-react';

class UserDetailsTable extends Component {

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
                        <Table.HeaderCell colSpan='2'>Your details</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    <Table.Row>
                        <Table.Cell>First name</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.userDetails.firstname)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Surname</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.userDetails.surname)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Address</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.userDetails.address)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>City</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.userDetails.city)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Zip code</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.userDetails.zipCode)}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Phone number</Table.Cell>
                        <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.userDetails.phoneNumber)}</Table.Cell>
                    </Table.Row>
                </Table.Body>
            </Table>
        );
    }
}

export default UserDetailsTable;