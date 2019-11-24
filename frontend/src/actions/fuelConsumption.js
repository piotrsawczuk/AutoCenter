import axios from 'axios';
import { API_URL, FUEL_ECONOMY_PAGE_SIZE as PAGE_SIZE } from '../utils/Properties';

const setFuelConsumptionList = (fuelConsumptionList) => {
    return {
        type: SET_FUEL_ECONOMY_LIST,
        fuelConsumptionList
    }
}

const setFuelConsumptionAvgs = (fuelConsumptionAvgs) => {
    return {
        type: SET_FUEL_ECONOMY_AVGS,
        fuelConsumptionAvgs
    }
}

const addFuelConsumption = (fuelConsumption) => {
    return {
        type: ADD_FUEL_ECONOMY,
        fuelConsumption
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_FUEL_ECONOMY_LIST = 'SET_FUEL_ECONOMY_LIST';
export const SET_FUEL_ECONOMY_AVGS = 'SET_FUEL_ECONOMY_AVGS';
export const ADD_FUEL_ECONOMY = 'ADD_FUEL_ECONOMY';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (carId, page) => {
    if (!page) page = 1;
    return dispatch => {
        axios.get(`${API_URL}/cars/${carId}/fuel-consumption?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            if (response.data.numberOfElements < 1 && page > 1)
                dispatch(findAll(carId, page-1));
            dispatch(setFuelConsumptionList(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const findAllAvgs = (carId) => {
    return dispatch => {
        axios.get(`${API_URL}/cars/${carId}/fuel-consumption/avg`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setFuelConsumptionAvgs(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const findAllAvgsByCarApiId = (modelId) => {
    return dispatch => {
        axios.get(`${API_URL}/fuel-consumption/avg?carApiId=${modelId}`)
        .then(response => {
            dispatch(setFuelConsumptionAvgs(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};

export const save = (carId, fuelConsumption) => {
    return dispatch => {
        axios.post(`${API_URL}/cars/${carId}/fuel-consumption`, fuelConsumption, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(addFuelConsumption(response.data));
            dispatch(findAllAvgs(carId));
            dispatch(findAll(carId));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}

export const deleteFuelConsumption = (carId, id, page) => {
    return dispatch => {
        axios.delete(`${API_URL}/cars/${carId}/fuel-consumption/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(findAllAvgs(carId));
            dispatch(findAll(carId, page+1));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}