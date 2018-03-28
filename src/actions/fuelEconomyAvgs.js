import axios from 'axios';

const url = 'http://localhost:8080/fuel-economy/avg';

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
        axios.get(`${url}?carApiId=${modelId}`).then(response => {
            dispatch(setFuelEconomyAvgs(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};