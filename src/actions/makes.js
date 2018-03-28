import axios from 'axios';

const url = 'http://localhost:8080/car-query-api/makes';

const setMakes = (makes) => {
    return {
        type: SET_MAKES,
        makes
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_MAKES = 'SET_MAKES';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (year) => {
    return dispatch => {
        axios.get(`${url}?year=${year}`).then(response => {
            dispatch(setMakes(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};