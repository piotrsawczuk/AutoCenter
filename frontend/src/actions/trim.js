import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setTrims = (trim) => {
    return {
        type: SET_TRIM,
        trim
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_TRIM = 'SET_TRIM';
export const SET_ERROR = 'SET_ERROR';

export const findOne = (modelId) => {
    return dispatch => {
        axios.get(`${API_URL}/car-query-api/trims/${modelId}`).then(response => {
            dispatch(setTrims(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};