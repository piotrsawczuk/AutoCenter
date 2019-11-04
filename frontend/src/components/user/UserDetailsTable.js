import React, { Component } from 'react';
import { Table } from 'semantic-ui-react';

class UserDetailsTable extends Component {

    render() {
        const { userDetails } = this.props;
        return (
            <Table color = {'blue'} key = {'blue'}>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell colSpan='2'>Account details</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    <Table.Row>
                        <Table.Cell>First name</Table.Cell>
                        <Table.Cell textAlign='right'>{userDetails.firstname || '-'}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Surname</Table.Cell>
                        <Table.Cell textAlign='right'>{userDetails.surname || '-'}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Address</Table.Cell>
                        <Table.Cell textAlign='right'>{userDetails.address || '-'}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>City</Table.Cell>
                        <Table.Cell textAlign='right'>{userDetails.city || '-'}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Zip code</Table.Cell>
                        <Table.Cell textAlign='right'>{userDetails.zipCode || '-'}</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Phone number</Table.Cell>
                        <Table.Cell textAlign='right'>{userDetails.phoneNumber || '-'}</Table.Cell>
                    </Table.Row>
                </Table.Body>
            </Table>
        );
    }
}

export default UserDetailsTable;