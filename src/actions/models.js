import axios from 'axios';

const url = 'http://localhost:8080/car-query-api/models';

const setModels = (models) => {
    return {
        type: SET_MODELS,
        models
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_MODELS = 'SET_MODELS';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (year, make) => {
    return dispatch => {
        axios.get(`${url}?year=${year}&make=${make}`).then(response => {
            dispatch(setModels(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};