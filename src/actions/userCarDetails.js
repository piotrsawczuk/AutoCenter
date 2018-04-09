import axios from 'axios';

const url = 'http://localhost:8080/cars';

const setUserCarDetails = (userCarDetails) => {
    return {
        type: SET_USER_CAR_DETAILS,
        userCarDetails
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_USER_CAR_DETAILS = 'SET_USER_CAR_DETAILS';
export const SET_ERROR = 'SET_ERROR';

export const findOne = (carId) => {
    return dispatch => {
        axios.get(`${url}/${carId}/details`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setUserCarDetails(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};

export const save = (carId, data) => {
    return dispatch => {
        axios.post(`${url}/${carId}/details`, data, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};

export const edit = (carId, data) => {
    return dispatch => {
        axios.put(`${url}/${carId}/details`, data, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};