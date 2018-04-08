import { Component } from 'react';
import PropTypes from "prop-types";
import { connect } from 'react-redux';
import { logout } from '../actions/authentication';

class LogoutPage extends Component {
    
    static propTypes = {
        dispatch: PropTypes.func.isRequired
    };

    componentWillMount() {
        this.props.dispatch(logout());
        this.props.history.push('/');
    }

    render() {
        return null;
    }
}

export default connect() (LogoutPage);