import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setYears = (years) => {
    return {
        type: SET_YEARS,
        years
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_YEARS = 'SET_YEARS';
export const SET_ERROR = 'SET_ERROR';

export const findOne = () => {
    return dispatch => {
        axios.get(`${API_URL}/car-query-api/years`).then(response => {
            dispatch(setYears(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};