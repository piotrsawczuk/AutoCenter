import { LOGIN_SUCCESS, LOGIN_FAILURE, LOGOUT_SUCCESS, SET_ERROR } from "../actions/authentication";

const authReducer = (

    state = {
        token: localStorage.getItem('token'),
        errorMessage: '',
        isAuthenticated: localStorage.getItem('token') ? true : false,
        status: 0
    }
    , action = {}) => {
    switch (action.type) {

        case LOGIN_SUCCESS :
            return {
                ...state,
                isAuthenticated: true,
                token: action.token
            };

        case LOGIN_FAILURE :
            return {
                ...state,
                isAuthenticated: false,
                errorMessage: action.message
            };

        case LOGOUT_SUCCESS :
            return {
                ...state,
                token: localStorage.getItem('token'),
                isAuthenticated: false
            };

        case SET_ERROR :
            return {
                ...state,
                isAuthenticated: false,
                error: action.error
            };

        default : return state;
    }
}

export default authReducer;