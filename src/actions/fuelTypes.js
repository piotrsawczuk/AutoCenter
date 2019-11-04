import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setFuelTypes = (fuelTypes) => {
    return {
        type: SET_FUEL_TYPES,
        fuelTypes
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_FUEL_TYPES = 'SET_FUEL_TYPES';
export const SET_ERROR = 'SET_ERROR';

export const findAll = () => {
    return dispatch => {
        axios.get(`${API_URL}/fuel-types`).then(response => {
            dispatch(setFuelTypes(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};