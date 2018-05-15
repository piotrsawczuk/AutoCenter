import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Grid, Breadcrumb, Message } from 'semantic-ui-react';
import PasswordChangeForm from '../components/user/PasswordChangeForm';
import EmailChangeForm from '../components/user/EmailChangeForm';
import { edit } from '../services/UserService';

class PasswordEmailChangePage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isAccountEdited: false,
            error: {}
        }
    }

    onSubmit = async (data) => {
        try {
            const status = await edit(data)
            if (status === 201)
                this.setState({isAccountEdited: true});
        } catch(error) {
            this.setState({error});
        }
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
            :
                this.state.isAccountEdited ?
                    <div>
                        <Redirect to='/accountDetails'/>
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
                            <div style={{marginTop: '20px'}}>
                                {this.state.error.message && <Message error header="Error!" content={this.state.error.message}/> }
                            </div>
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