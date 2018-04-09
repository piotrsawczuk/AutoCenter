import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Grid, Breadcrumb } from 'semantic-ui-react';
import PasswordChangeForm from '../components/user/PasswordChangeForm';
import EmailChangeForm from '../components/user/EmailChangeForm';
import { edit } from '../actions/users';

class PasswordEmailChangePage extends Component {

    onSubmit = (data) => {
        this.props.edit(data);
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
                            <Breadcrumb.Section active>Change password / email</Breadcrumb.Section>
                        </Breadcrumb>
                    </div>
                    <div>
                        <Grid columns={2} centered={true} style={{ height: '100%' }} verticalAlign='top'>
                            <Grid.Column>
                                <PasswordChangeForm onSubmit = {this.onSubmit.bind(this)} />
                            </Grid.Column>
                            <Grid.Column>
                                <EmailChangeForm onSubmit = {this.onSubmit.bind(this)} />
                            </Grid.Column>
                        </Grid>
                    </div>
                </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps, { edit }) (PasswordEmailChangePage);