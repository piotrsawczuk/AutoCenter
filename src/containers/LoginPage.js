import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Grid } from 'semantic-ui-react'
import LoginForm from '../components/auth/LoginForm';
import { login } from '../actions/authentication';

class LoginPage extends Component {
    
    onSubmit = (data) => {
        this.props.login(data);
    }

    render() {
        return (
            this.props.isAuthenticated ?
                <div>
                    <Redirect to='/cars'/>
                </div>
            :
                <div>
                    <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
                        <Grid.Column style={{ maxWidth: 450 }}>
                            <LoginForm onSubmit = {this.onSubmit.bind(this)} />
                        </Grid.Column>
                    </Grid>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps, { login }) (LoginPage);