import axios from 'axios';
import { API_URL, FUEL_ECONOMY_PAGE_SIZE as PAGE_SIZE } from '../utils/Properties';

export const findAll = (carId, page) => new Promise((resolve, reject) => {
    if (!page) page = 1;
    axios.get(`${API_URL}/cars/${carId}/fuel-consumption?page=${page-1}&size=${PAGE_SIZE}`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        if (response.data.numberOfElements < 1 && page > 1)
            this.findAll(carId, page-1);
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findAllAvgs = (carId) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/cars/${carId}/fuel-consumption/avg`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findAllAvgsByCarApiId = (modelId) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/fuel-consumption/avg?carApiId=${modelId}`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const save = (carId, data) => new Promise((resolve, reject) => {
    axios.post(`${API_URL}/cars/${carId}/fuel-consumption`, data, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data)
    }); 
});

export const deleteFuelConsumption = (carId, id) => new Promise((resolve, reject) => {
    axios.delete(`${API_URL}/cars/${carId}/fuel-consumption/${id}`, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.status);
    }).catch(error => {
        reject(error.response.data);
    }); 
});