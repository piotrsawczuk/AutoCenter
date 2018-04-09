import axios from 'axios';

const url = 'http://localhost:8080/cars';
const PAGE_SIZE = 4;

const setUserCars = (userCars) => {
    return {
        type: SET_USER_CARS,
        userCars
    }
}

const setUserCar = (userCar) => {
    return {
        type: SET_USER_CAR,
        userCar
    }
}

const addUserCar = (userCar) => {
    return {
        type: ADD_USER_CAR,
        userCar
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_USER_CARS = 'SET_USER_CARS';
export const SET_USER_CAR = 'SET_USER_CAR';
export const ADD_USER_CAR = 'ADD_USER_CAR';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (page) => {
    if (!page) page = 1;
    return dispatch => {
        axios.get(`${url}?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            if (response.data.numberOfElements < 1 && page > 1)
                dispatch(findAll(page-1));
            dispatch(setUserCars(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const findOne = (id) => {
    return dispatch => {
        axios.get(`${url}/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setUserCar(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const save = (userCar) => {
    return dispatch => {
        axios.post(url, userCar, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(addUserCar(response.data));
            dispatch(findAll());
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}

export const deleteCar = (id, page) => {
    return dispatch => {
        axios.delete(`${url}/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(findAll(page+1));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}