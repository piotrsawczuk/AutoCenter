import axios from 'axios';
import { API_URL } from '../utils/Properties';

export const findOne = () => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/fuel-types`)
    .then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});