import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setDrivingTypes = (drivingTypes) => {
    return {
        type: SET_DRIVING_TYPES,
        drivingTypes
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_DRIVING_TYPES = 'SET_DRIVING_TYPES';
export const SET_ERROR = 'SET_ERROR';

export const findAll = () => {
    return dispatch => {
        axios.get(`${API_URL}/driving-types`).then(response => {
            dispatch(setDrivingTypes(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};