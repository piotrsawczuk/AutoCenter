import React, { Component } from 'react';
import { connect } from 'react-redux';
import LoginForm from '../components/login/LoginForm';
import { login } from '../actions/authorization';

class LoginPage extends Component {
    onSubmit = (data) => {
        this.props.login(data);
    }

    render() {
        return (
            <div>
                <LoginForm onSubmit = {this.onSubmit.bind(this)} />
                {console.log(this.props.token)}
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