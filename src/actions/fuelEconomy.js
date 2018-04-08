import axios from 'axios';

const url = 'http://localhost:8080/cars';
const PAGE_SIZE = 10;

const setfuelEconomyList = (fuelEconomyList) => {
    return {
        type: SET_FUEL_ECONOMY_LIST,
        fuelEconomyList
    }
}

const setfuelEconomyAvgs = (fuelEconomyAvgs) => {
    return {
        type: SET_FUEL_ECONOMY_AVGS,
        fuelEconomyAvgs
    }
}

const addfuelEconomy = (fuelEconomy) => {
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
            dispatch(setfuelEconomyList(response.data));
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
            dispatch(setfuelEconomyAvgs(response.data));
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
            dispatch(addfuelEconomy(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}

export const deleteFuelEconomy = (carId, id) => {
    return dispatch => {
        axios.delete(`${url}/${carId}/fuel-economy/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}