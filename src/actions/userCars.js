import axios from 'axios';

const url = 'http://localhost:8080/cars';
const PAGE_SIZE = 5;

const setUserCars = (userCars) => {
    return {
        type: SET_USER_CARS,
        userCars
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_USER_CARS = 'SET_USER_CARS';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (page) => {
    if (!page) page = 1;
    return dispatch => {
        axios.get(`${url}?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setUserCars(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};