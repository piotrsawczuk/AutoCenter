import { LOGIN_SUCCESS, LOGOUT_SUCCESS } from "../actions/authentication";

const authReducer = (
    state = {
        token: localStorage.getItem('token'),
        isAuthenticated: localStorage.getItem('token') ? true : false
    }
    , action = {}) => {
    switch (action.type) {

        case LOGIN_SUCCESS :
            return {
                ...state,
                isAuthenticated: true,
                token: action.token
            };

        case LOGOUT_SUCCESS :
            return {
                ...state,
                isAuthenticated: false,
                token: {}
            };

        default : return state;
    }
}

export default authReducer;