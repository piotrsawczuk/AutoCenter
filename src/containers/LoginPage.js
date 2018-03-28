import React, { Component } from 'react';
import { connect } from 'react-redux';
import LoginForm from '../components/login/LoginForm';
import { login } from '../actions/authentication';

class LoginPage extends Component {
    onSubmit = (data) => {
        this.props.login(data);
        this.props.history.push('/');
    }

    render() {
        return (
            <div>
                <LoginForm onSubmit = {this.onSubmit.bind(this)} />
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        token : state.authReducer.token
    }
}

export default connect(mapStateToProps, {login}) (LoginPage);