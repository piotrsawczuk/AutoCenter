import axios from 'axios';
import { API_URL } from '../utils/Properties';

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
        axios.get(`${API_URL}/car-query-api/models?year=${year}&make=${make}`).then(response => {
            dispatch(setModels(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};