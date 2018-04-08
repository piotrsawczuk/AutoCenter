import axios from 'axios';

const url = 'http://localhost:8080/users';

const setError = (error) => {
    return {
        type: SET_ERROR,
        error
    }
}

export const SET_ERROR = 'SET_ERROR';

export const edit = (data) => {
    return dispatch => {
        axios.put(url, data, { headers: {
            'Authorization': localStorage.getItem('token')
        }}).catch(error => {
            dispatch(setError(error.response.data))
        });
    }
};