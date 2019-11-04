import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb } from 'semantic-ui-react';
import UserDetailsForm from '../components/user/UserDetailsForm';
import { findOne as findUserDetails, save as saveUserDetails, edit as editUserDetails } from '../actions/userDetails';

class EditUserDetailsPage extends Component {

    componentDidMount = () => {
        this.props.findUserDetails();
    }

    onSubmit = (data) => {
        if (this.props.userDetails)
            this.props.editUserDetails(data);
        else
            this.props.saveUserDetails(data);
        this.props.history.push('/accountDetails');
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
                            <Breadcrumb.Section><Link to='/accountDetails'>Back to your account</Link></Breadcrumb.Section>
                            <Breadcrumb.Divider icon='right chevron' />
                            <Breadcrumb.Section active>Edit account details</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    <div>
                        <UserDetailsForm userDetails = {this.props.userDetails} onSubmit = {this.onSubmit.bind(this)} />
                    </div>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        userDetails: state.userDetailsReducer.userDetails
    }
}

export default connect(mapStateToProps, { findUserDetails, saveUserDetails, editUserDetails }) (EditUserDetailsPage);