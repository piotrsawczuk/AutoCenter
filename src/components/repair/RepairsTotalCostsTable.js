import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Table } from 'semantic-ui-react';
import { findAll as findAllExploitationTypes} from '../../actions/exploitationTypes';

class RepairsTotalCostsTable extends Component {

    componentDidMount = () => {
        this.props.findAllExploitationTypes();
    }

    getRepairsTotalCostValue = (repairsCostsCollection, exploitationTypeValue) => {
        if (!repairsCostsCollection || repairsCostsCollection.length <= 0)
            return '-';
        for (var i=0; i < repairsCostsCollection.length; i++) {
            if (repairsCostsCollection[i].exploitationTypeValue === exploitationTypeValue) {
                return repairsCostsCollection[i].totalCost;
            }
        }
        return '-';
    }

    render() {
        if (this.props.exploitationTypes
            && this.props.exploitationTypes.length > 0
        ) {
            return (
                <Table celled compact definition>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell />
                            <Table.HeaderCell>{this.props.exploitationTypes[0].exploitationType}</Table.HeaderCell>
                            <Table.HeaderCell>{this.props.exploitationTypes[1].exploitationType}</Table.HeaderCell>
                            <Table.HeaderCell>{this.props.exploitationTypes[2].exploitationType}</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        <Table.Row>
                            <Table.Cell>Total cost</Table.Cell>
                            <Table.Cell>{this.getRepairsTotalCostValue(this.props.repairsTotalCosts, this.props.exploitationTypes[0].value)}</Table.Cell>
                            <Table.Cell>{this.getRepairsTotalCostValue(this.props.repairsTotalCosts, this.props.exploitationTypes[1].value)}</Table.Cell>
                            <Table.Cell>{this.getRepairsTotalCostValue(this.props.repairsTotalCosts, this.props.exploitationTypes[2].value)}</Table.Cell>
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
        exploitationTypes : state.exploitationTypesReducer.exploitationTypes
    }
}

export default connect(mapStateToProps,
    {
        findAllExploitationTypes
    }
) (RepairsTotalCostsTable);