import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Grid, Message } from 'semantic-ui-react'
import SignUpForm from '../components/auth/SignUpForm';
import { register, login } from '../services/AuthenticationService';
import { setToken } from '../actions/authentication';

class SignUpPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            error: {}
        }
    }

    onSubmit = async (data) => {
        try {
            const status = await register(data);
            if (status === 201) {
                const token = await login(data);
                if (token) this.props.setToken(`Bearer ${token}`);
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
                            <SignUpForm onSubmit = {this.onSubmit.bind(this)} />
                        </div>
                        <div>
                            {this.state.error.message && <Message error header="Error!" content={this.state.error.message}/> }
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

export default connect(mapStateToProps, { setToken }) (SignUpPage);