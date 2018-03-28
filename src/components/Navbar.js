import React from 'react';
import { NavLink } from 'react-router-dom';
import { Menu } from 'semantic-ui-react';

const Navbar = () => {
    return (
        <Menu color={'blue'} inverted style = {{ borderRadius : 0 }}>
            <Menu.Menu position='left'>
                <Menu.Item as = {NavLink} to = '/'>Home</Menu.Item>
            </Menu.Menu>
            <Menu.Menu position='right'>
                <Menu.Item as = {NavLink} to = '/login'>Login</Menu.Item>
            </Menu.Menu>
        </Menu>
    );
};

export default Navbar;  