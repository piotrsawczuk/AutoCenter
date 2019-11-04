import axios from 'axios';
import { API_URL, REPAIRS_PAGE_SIZE as PAGE_SIZE } from '../utils/Properties';

export const findAll = (carId, page) => new Promise((resolve, reject) => {
    if (!page) page = 1;
    axios.get(`${API_URL}/cars/${carId}/repairs?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        if (response.data.numberOfElements < 1 && page > 1)
            this.findAll(carId, page-1);
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findTotalCosts = (carId) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/cars/${carId}/repairs/total-cost`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findTotalCostsByCarApiId = (modelId) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/repairs/total-cost?carApiId=${modelId}`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const save = (carId, data) => new Promise((resolve, reject) => {
    axios.post(`${API_URL}/cars/${carId}/repairs`, repair, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data)
    }); 
});

export const deleteRepair = (carId, id) => new Promise((resolve, reject) => {
    axios.delete(`${API_URL}/cars/${carId}/repairs/${id}`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.status);
    }).catch(error => {
        reject(error.response.data);
    }); 
});