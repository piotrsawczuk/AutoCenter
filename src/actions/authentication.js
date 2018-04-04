import axios from 'axios';

const loginUrl = 'http://localhost:8080/login';
const registerUrl = 'http://localhost:8080/register';

const TOKEN_AUTH_USERNAME = 'autocenterjwtclientid';
const TOKEN_AUTH_PASSWORD = 'XY7kmzoNzl100';

const receiveLogin = (token) => {
    return {
        type: LOGIN_SUCCESS,
        isAuthenticated: true,
        token
    }
}

const receiveLogout = () => {
    return {
        type: LOGOUT_SUCCESS,
        isAuthenticated: false
    }
}

const loginError = (message) => {
    return {
        type: LOGIN_FAILURE,
        isAuthenticated: false,
        message
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';
export const LOGOUT_SUCCESS = 'LOGOUT_SUCCESS';
export const SET_ERROR = 'SET_ERROR';

export const login = (data) => {
    return async (dispatch) => {
        try {
            const body = `username=${encodeURIComponent(data.username)}&password=${encodeURIComponent(data.password)}&grant_type=password`;
            const response = await axios.post(loginUrl, body, { headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD)
            } });
            localStorage.setItem('token', `Bearer ${response.data.access_token}`);
            dispatch(receiveLogin(`Bearer ${response.data.access_token}`));
        } catch (error) {
            dispatch(loginError(error.response.data.error_description));
        }
    }
};

export const register = (data) => {
    return dispatch => {
        axios.post(registerUrl, data).then(response => {
            dispatch(login(data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
}

export const logout = () => {
    return dispatch => {
        try {
            localStorage.removeItem('token');
            dispatch(receiveLogout());
        } catch (error) {
            dispatch(setError(error));
        }
    }
};