import axios from 'axios';
import { API_URL } from '../utils/Properties';

export const findOne = (id) => new Promise((resolve, reject) => {
    axios.get(`${API_URL}/cars/${id}`, { headers: {
        'Authorization': localStorage.getItem('token')
    } }).then(response => {
        resolve(response.data);
    }).catch(error => {
        reject(error.response.data);
    });
});