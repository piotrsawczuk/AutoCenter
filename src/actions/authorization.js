import axios from 'axios';

const url = 'http://localhost:8080/login';

const TOKEN_AUTH_USERNAME = 'autocenterjwtclientid';
const TOKEN_AUTH_PASSWORD = 'XY7kmzoNzl100';

const setToken = (token) => {
    return {
        type: SET_TOKEN,
        token
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_TOKEN = 'SET_TOKEN';
export const SET_ERROR = 'SET_ERROR';

export const login = (data) => {
    return async (dispatch) => {
        try {
            const body = `username=${encodeURIComponent(data.username)}&password=${encodeURIComponent(data.password)}&grant_type=password`;
            const tokenData = await axios.post(url, body, { headers: { 
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD)
            } });
            dispatch(setToken(`Bearer ${tokenData.data.access_token}`));
        } catch (error) {
            dispatch(setError(error));
        }
    }
};