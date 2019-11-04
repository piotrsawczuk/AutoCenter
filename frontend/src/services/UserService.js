import axios from 'axios';
import { API_URL } from '../utils/Properties';

export const edit = (data) => new Promise((resolve, reject) => {
    axios.put(`${API_URL}/users`, data, { headers: {
        'Authorization': localStorage.getItem('token')
    }}).then(response => {
        resolve(response.status);
    }).catch(error => {
        reject(error.response.data);
    });
});