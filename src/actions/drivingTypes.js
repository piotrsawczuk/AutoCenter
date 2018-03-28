import axios from 'axios';

const url = 'http://localhost:8080/driving-types';

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
        axios.get(url).then(response => {
            dispatch(setDrivingTypes(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};