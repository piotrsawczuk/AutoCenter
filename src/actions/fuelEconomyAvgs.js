import axios from 'axios';
import { API_URL } from '../utils/Properties';

const setFuelEconomyAvgs = (fuelEconomyAvgs) => {
    return {
        type: SET_FUEL_ECONOMY_AVGS,
        fuelEconomyAvgs
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_FUEL_ECONOMY_AVGS = 'SET_FUEL_ECONOMY_AVGS';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (modelId) => {
    return dispatch => {
        axios.get(`${API_URL}/fuel-economy/avg?carApiId=${modelId}`).then(response => {
            dispatch(setFuelEconomyAvgs(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};