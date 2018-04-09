import axios from 'axios';

const url = 'http://localhost:8080/cars';
const PAGE_SIZE = 10;

const setFuelEconomyList = (fuelEconomyList) => {
    return {
        type: SET_FUEL_ECONOMY_LIST,
        fuelEconomyList
    }
}

const setFuelEconomyAvgs = (fuelEconomyAvgs) => {
    return {
        type: SET_FUEL_ECONOMY_AVGS,
        fuelEconomyAvgs
    }
}

const addFuelEconomy = (fuelEconomy) => {
    return {
        type: ADD_FUEL_ECONOMY,
        fuelEconomy
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
        axios.get(`${url}/${carId}/fuel-economy?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            if (response.data.numberOfElements < 1 && page > 1)
                dispatch(findAll(carId, page-1));
            dispatch(setFuelEconomyList(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const findAllAvgs = (carId) => {
    return dispatch => {
        axios.get(`${url}/${carId}/fuel-economy/avg`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setFuelEconomyAvgs(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const save = (carId, fuelEconomy) => {
    return dispatch => {
        axios.post(`${url}/${carId}/fuel-economy`, fuelEconomy, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(addFuelEconomy(response.data));
            dispatch(findAllAvgs(carId));
            dispatch(findAll(carId));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}

export const deleteFuelEconomy = (carId, id, page) => {
    return dispatch => {
        axios.delete(`${url}/${carId}/fuel-economy/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(findAllAvgs(carId));
            dispatch(findAll(carId, page+1));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}