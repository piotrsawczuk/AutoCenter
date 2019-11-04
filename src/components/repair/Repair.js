import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Button, Table } from 'semantic-ui-react';
import { deleteRepair } from '../../actions/repairs';

class Repair extends Component {

    state = { 
        isDeleted: false
    }
    
    deleteRepair = () => {
        this.props.deleteRepair(this.props.car.id, this.props.repair.id, this.props.page);
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
                    <Redirect to={`/cars/${this.props.car.id}/repairs`}/>
                </Table.Row>
            :
                <Table.Row>
                    <Table.Cell>{this.checkIfValueExists(this.props.repair.date)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.repair.exploitationType.exploitationType)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.repair.mileage)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.repair.cost)}</Table.Cell>
                    <Table.Cell>{this.checkIfValueExists(this.props.repair.description)}</Table.Cell>
                    <Table.Cell>
                        <Button.Group primary vertical={true} size='mini'  floated={'right'} compact={false}>
                            {/* <Button>Edit report</Button> */}
                            <Button onClick={() => this.deleteRepair()}>Delete repair</Button>
                        </Button.Group>
                    </Table.Cell>
                </Table.Row>
        );
    }
};

const mapStateToProps = (state) => {
    return {
        repairs: state.repairsReducer.repairs,
        car: state.carsReducer.car
    }
}

export default connect(mapStateToProps, { deleteRepair }) (Repair);