import React, { Component } from 'react';
import { connect } from 'react-redux';
import { NavLink } from 'react-router-dom';
import { Menu } from 'semantic-ui-react';

class Navbar extends Component {

    render() {
        return (
            <Menu stackable color={'blue'} inverted style = {{ borderRadius : 0 }}>
                <Menu.Menu position='left'>
                    <Menu.Item as = {NavLink} to = '/'>Home</Menu.Item>
                </Menu.Menu>
                <Menu.Menu position='right'>
                    {this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/cars'>My cars</Menu.Item>}
                    {this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/accountDetails'>Your account</Menu.Item>}
                    {this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/logout'>Logout</Menu.Item>}
                    {!this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/register'>Sign up</Menu.Item>}
                    {!this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/login'>Log in</Menu.Item>}
                </Menu.Menu>
            </Menu>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps) (Navbar);