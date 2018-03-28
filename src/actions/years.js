import axios from 'axios';

const url = 'http://localhost:8080/car-query-api/years';

const setYears = (years) => {
    return {
        type: SET_YEARS,
        years
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_YEARS = 'SET_YEARS';
export const SET_ERROR = 'SET_ERROR';

export const findOne = () => {
    return dispatch => {
        axios.get(url).then(response => {
            dispatch(setYears(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};