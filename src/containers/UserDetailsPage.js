import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Redirect } from 'react-router';
import { Button } from 'semantic-ui-react';
import UserDetailsTable from '../components/user/UserDetailsTable';
import { findOne as findUserDetails } from '../actions/userDetails';

class UserDetailsPage extends Component {
    componentDidMount = () => {
        this.props.findUserDetails();
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
            :
                <div>
                    <div style={{marginBottom: '10px', display: 'inline-block'}}>
                        <Link className="ui large primary left floated button" to="/accountDetails/edit">Edit account details</Link>
                        {/* <Link className="ui large primary left floated button" to="/accountDetails/changePassword">Change password</Link> */}
                    </div>
                    <div>
                    <UserDetailsTable userDetails = {this.props.userDetails}/>
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

export default connect(mapStateToProps, {findUserDetails}) (UserDetailsPage);