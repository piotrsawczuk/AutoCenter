import axios from 'axios';

const url = 'http://localhost:8080/cars';
const PAGE_SIZE = 10;

const setRepairs = (repairs) => {
    return {
        type: SET_REPAIRS,
        repairs
    }
}

const setRepairsTotalCosts = (repairsTotalCosts) => {
    return {
        type: SET_REPAIRS_TOTAL_COSTS,
        repairsTotalCosts
    }
}

const addRepair = (repair) => {
    return {
        type: ADD_REPAIR,
        repair
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_REPAIRS = 'SET_REPAIRS';
export const SET_REPAIRS_TOTAL_COSTS = 'SET_REPAIRS_TOTAL_COSTS';
export const ADD_REPAIR = 'ADD_REPAIR';
export const SET_ERROR = 'SET_ERROR';

export const findAll = (carId, page) => {
    if (!page) page = 1;
    return dispatch => {
        axios.get(`${url}/${carId}/repairs?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setRepairs(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const findTotalCosts = (carId) => {
    return dispatch => {
        axios.get(`${url}/${carId}/repairs/total-cost`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setRepairsTotalCosts(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
}

export const save = (carId, repair) => {
    return dispatch => {
        axios.post(`${url}/${carId}/repairs`, repair, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).then(response => {
            dispatch(addRepair(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}

export const deleteRepair = (carId, id) => {
    return dispatch => {
        axios.delete(`${url}/${carId}/repairs/${id}`, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).catch(error => {
            dispatch(setError(error.response.data.error))
        }); 
    }
}