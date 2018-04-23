import axios from 'axios';
import { API_URL } from '../utils/Properties';

const PAGE_SIZE = 4;

const setCars = (cars) => {
    return {
        type: SET_CARS,
        cars
    }
}

const setCar = (car) => {
    return {
        type: SET_CAR,
        car
    }
}

const addCar = (car) => {
    return {
        type: ADD_CAR,
        car
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_CARS = 'SET_CARS';
export const SET_CAR = 'SET_CAR';
export const ADD_CAR = 'ADD_CAR';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (page) => {
    if (!page) page = 1;
    return dispatch => {
        axios.get(`${API_URL}/cars?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            if (response.data.numberOfElements < 1 && page > 1)
                dispatch(findAll(page-1));
            dispatch(setCars(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const findOne = (id) => {
    return dispatch => {
        axios.get(`${API_URL}/cars/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setCar(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const save = (car) => {
    return dispatch => {
        axios.post(`${API_URL}/cars`, car, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(addCar(response.data));
            dispatch(findAll());
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}

export const deleteCar = (id, page) => {
    return dispatch => {
        axios.delete(`${API_URL}/cars/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(findAll(page+1));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}