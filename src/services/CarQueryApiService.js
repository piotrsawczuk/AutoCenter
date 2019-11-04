import axios from 'axios';
import { API_URL } from '../utils/Properties';

export const findYears = () => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/car-query-api/years`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findMakes = (year) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/car-query-api/makes?year=${year}`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findModels = (year, make) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/car-query-api/models?year=${year}&make=${make}`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findTrims = (year, make, model) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/car-query-api/trims?year=${year}&make=${make}&model=${model}`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const findTrim = (modelId) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/car-query-api/trims/${modelId}`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});