import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import SignUpForm from '../components/auth/SignUpForm';
import { register } from '../actions/authentication';

class SignUpPage extends Component {
    onSubmit = (data) => {
        this.props.register(data);
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <SignUpForm onSubmit = {this.onSubmit.bind(this)} />
                </div>
            :
                <div>
                    <Redirect to='/'/>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated : state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps, {register}) (SignUpPage);