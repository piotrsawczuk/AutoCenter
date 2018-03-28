import React, { Component } from 'react';
import { connect } from 'react-redux';
import { NavLink } from 'react-router-dom';
import { Menu } from 'semantic-ui-react';

class Navbar extends Component {
    render() {
        return (
            <Menu color={'blue'} inverted style = {{ borderRadius : 0 }}>
                <Menu.Menu position='left'>
                    <Menu.Item as = {NavLink} to = '/'>Home</Menu.Item>
                </Menu.Menu>
                <Menu.Menu position='right'>
                    {!this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/signup'>Sign up</Menu.Item>}
                    {!this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/login'>Log in</Menu.Item>}
                    {this.props.isAuthenticated && <Menu.Item as = {NavLink} to = '/logout'>Logout</Menu.Item>}
                </Menu.Menu>
            </Menu>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated : state.authReducer.isAuthenticated
    }
}

export default connect(mapStateToProps)(Navbar);