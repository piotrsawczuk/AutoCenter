import axios from 'axios';
import { API_URL } from '../utils/Properties';

export const findOne = () => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/user-details`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const save = (data) => new Promise((resolve, reject) => {
    axios.post(`${API_URL}/user-details`, data, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const edit = (data) => new Promise((resolve, reject) => {
    axios.put(`${API_URL}/user-details`, data, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});