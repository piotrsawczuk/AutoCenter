import axios from 'axios';
import { API_URL, CARS_PAGE_SIZE as PAGE_SIZE } from '../utils/Properties';

export const findAll = (page) => new Promise((resolve, reject) => {
    if (!page) page = 1;
    axios.get(`${API_URL}/cars?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        if (response.data.numberOfElements < 1 && page > 1)
            this.findAll(page-1);
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findOne = (id) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/cars/${id}`, { headers: {
        'Authorization': localStorage.getItem('token')
    } }).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const save = (data) => new Promise((resolve, reject) => {
    axios.post(`${API_URL}/cars`, data, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.status);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const deleteCar = (id) => new Promise((resolve, reject) => {
    axios.delete(`${API_URL}/cars/${id}`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.status);
    }).catch(error => {
        reject(error.response.data);
    });
});