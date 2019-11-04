import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setTrims = (trims) => {
    return {
        type: SET_TRIMS,
        trims
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_TRIMS = 'SET_TRIMS';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (year, make, model) => {
    return dispatch => {
        axios.get(`${API_URL}/car-query-api/trims?year=${year}&make=${make}&model=${model}`).then(response => {
            dispatch(setTrims(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};