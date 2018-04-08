import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import RepairForm from '../components/repair/RepairForm';
import { save as addRepair } from '../actions/repairs';

class AddRepairPage extends Component {
    state = {
        isRepairAdded: false
    }

    onSubmit = (data) => {
        this.props.addRepair(this.props.match.params.carId, data);
        this.props.history.push(`/cars/${this.props.match.params.carId}/repairs`);
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
            :
                <div>
                    <div style={{marginBottom: '30px'}}>
                        <Breadcrumb size='big'>
                            <Breadcrumb.Section><Link to={`/cars/${this.props.match.params.carId}/repairs`}>Back to repairs</Link></Breadcrumb.Section>
                            <Breadcrumb.Divider icon='right chevron' />
                            <Breadcrumb.Section active>Add repair</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    <div>
                        <RepairForm onSubmit = {this.onSubmit.bind(this)} />
                    </div>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        repair: state.userCarsReducer.repair
    }
}

export default connect(mapStateToProps,
    {
        addRepair
    }
) (AddRepairPage);