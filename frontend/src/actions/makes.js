import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setMakes = (makes) => {
    return {
        type: SET_MAKES,
        makes
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_MAKES = 'SET_MAKES';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (year) => {
    return dispatch => {
        axios.get(`${API_URL}/car-query-api/makes?year=${year}`).then(response => {
            dispatch(setMakes(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};