import axios from 'axios';
import { API_URL } from '../utils/Properties';

export const findOne = (carId) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/cars/${carId}/details`, { headers: {
        'Authorization': localStorage.getItem('token')
    } }).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const save = (carId, data) => new Promise((resolve, reject) => {
    axios.post(`${API_URL}/cars/${carId}/details`, data, { headers: {
        'Authorization': localStorage.getItem('token')
    } }).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const edit = (carId, data) => new Promise((resolve, reject) => {
    axios.put(`${API_URL}/cars/${carId}/details`, data, { headers: {
        'Authorization': localStorage.getItem('token')
    } }).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});