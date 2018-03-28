import axios from 'axios';

const url = 'http://localhost:8080/repairs/total-cost';

const setRepairsTotalCosts = (repairsTotalCosts) => {
    return {
        type: SET_REPAIRS_TOTAL_COSTS,
        repairsTotalCosts
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_REPAIRS_TOTAL_COSTS = 'SET_REPAIRS_TOTAL_COSTS';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (modelId) => {
    return dispatch => {
        axios.get(`${url}?carApiId=${modelId}`).then(response => {
            dispatch(setRepairsTotalCosts(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        })
    }
};