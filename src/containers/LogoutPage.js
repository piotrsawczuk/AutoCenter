import { Component } from 'react';
import PropTypes from "prop-types";
import { connect } from 'react-redux';
import { logout } from '../services/AuthenticationService';
import { removeToken } from '../actions/authentication';

class LogoutPage extends Component {
    
    static propTypes = {
        dispatch: PropTypes.func.isRequired
    };

    componentWillMount = async () => {
        try {
            const isLoggedOut = await logout();
            if (isLoggedOut === true) {
                await this.props.dispatch(removeToken());
                this.props.history.push('/');
            }
        } catch (error) {
            console.log(error);
        }
    }

    render() {
        return null;
    }
}

export default connect() (LogoutPage);