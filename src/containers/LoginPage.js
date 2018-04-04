import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import LoginForm from '../components/auth/LoginForm';
import { login } from '../actions/authentication';

class LoginPage extends Component {
    onSubmit = (data) => {
        this.props.login(data);

    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <LoginForm onSubmit = {this.onSubmit.bind(this)} />
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
        token : state.authReducer.token,
        isAuthenticated : state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps, {login}) (LoginPage);