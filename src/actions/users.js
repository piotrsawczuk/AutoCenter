import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_ERROR = 'SET_ERROR';

export const edit = (data) => {
    return dispatch => {
        axios.put(`${API_URL}/users`, data, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};