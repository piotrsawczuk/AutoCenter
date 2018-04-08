import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Grid } from 'semantic-ui-react'
import SignUpForm from '../components/auth/SignUpForm';
import { register } from '../actions/authentication';

class SignUpPage extends Component {
    
    onSubmit = (data) => {
        this.props.register(data);
    }

    render() {
        return (
            this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
            :
                <Grid centered={true} style={{ height: '100%' }} verticalAlign='middle'>
                    <Grid.Column style={{ maxWidth: 450 }}>
                        <SignUpForm onSubmit = {this.onSubmit.bind(this)} />
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

export default connect(mapStateToProps, { register }) (SignUpPage);