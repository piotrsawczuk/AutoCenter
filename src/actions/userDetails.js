import axios from 'axios';

const url = 'http://localhost:8080/user-details';

const setUserDetails = (userDetails) => {
    return {
        type: SET_USER_DETAILS,
        userDetails
    }
}

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_USER_DETAILS = 'SET_USER_DETAILS';
export const SET_ERROR = 'SET_ERROR';

export const findOne = () => {
    return dispatch => {
        axios.get(url, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setUserDetails(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};

export const save = (data) => {
    return dispatch => {
        axios.post(url, data, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setUserDetails(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};

export const edit = (data) => {
    return dispatch => {
        axios.put(url, data, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(response => {
            dispatch(setUserDetails(response.data));
        }).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};