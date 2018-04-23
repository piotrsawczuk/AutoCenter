import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import RepairsTable from '../components/repair/RepairsTable';
import RepairsTotalCostsTable from '../components/repair/RepairsTotalCostsTable';
import { findAll as findRepairs, findTotalCosts as findRepairsTotalCosts } from '../actions/repairs';
import { findOne as findUserCar } from '../actions/cars';

class RepairsPage extends Component {
    
    componentDidMount = () => {
        this.props.findUserCar(this.props.match.params.carId);
        this.props.findRepairsTotalCosts(this.props.match.params.carId);
        this.props.findRepairs(this.props.match.params.carId);
    }

    shouldComponentUpdate = (nextProps, nextState) => {
        if (nextProps.repairsTotalCosts !== this.props.repairsTotalCosts || nextProps.repairs !== this.props.repairs)
            return true;
        else
            return false;
    }

    handlePaginationChange = (e, page) => {
        this.props.findRepairs(this.props.match.params.carId, page.activePage);
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
                            <Breadcrumb.Section><Link to="/cars">Back to cars</Link></Breadcrumb.Section>
                            <Breadcrumb.Divider icon='right chevron' />
                            <Breadcrumb.Section active>{this.props.car.carName}</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    <h3>Repairs total costs</h3>
                    <RepairsTotalCostsTable repairsTotalCosts = {this.props.repairsTotalCosts} />
                    <h3>Repairs</h3>
                    <RepairsTable repairs = {this.props.repairs} carId = {this.props.match.params.carId} onPageChange = {this.handlePaginationChange}/>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        repairs: state.repairsReducer.repairs,
        repairsTotalCosts: state.repairsReducer.repairsTotalCosts,
        car: state.carsReducer.car
    }
}

export default connect(mapStateToProps, { findRepairs, findRepairsTotalCosts, findUserCar }) (RepairsPage);