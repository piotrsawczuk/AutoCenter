import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Grid, Message } from 'semantic-ui-react'
import LoginForm from '../components/auth/LoginForm';
import { login } from '../services/AuthenticationService';
import { setToken } from '../actions/authentication';

class LoginPage extends Component {
    
    constructor(props) {
        super(props);

        this.state = {
            error: {}
        }
    }

    onSubmit = async (data) => {
        try {
            const token = await login(data);
            if (token) {
                this.props.setToken(`Bearer ${token}`);
            }
        } catch (error) {
            this.setState({error});
        }
    }

    render() {
        return (
            this.props.isAuthenticated ?
                <div>
                    <Redirect to='/cars'/>
                </div>
            :
                <Grid centered={true} style={{ height: '100%' }} verticalAlign='middle'>
                    <Grid.Column style={{ maxWidth: 450 }}>
                        <div style={{marginBottom: '20px'}}>
                            <LoginForm onSubmit = {this.onSubmit.bind(this)} />
                        </div>
                        <div>
                            {this.state.error.error_description && <Message error header="Error!" content={this.state.error.error_description}/> }
                        </div>
                    </Grid.Column>
                </Grid>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps, { setToken }) (LoginPage);