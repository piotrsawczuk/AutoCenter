import axios from 'axios';
import { API_URL, TOKEN_AUTH_USERNAME, TOKEN_AUTH_PASSWORD } from '../utils/Properties';

export const login = (data) => new Promise((resolve, reject) => {
    const body = `username=${encodeURIComponent(data.username)}&password=${encodeURIComponent(data.password)}&grant_type=password`;
    axios.post(`${API_URL}/login`, body, { headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD)
    } }).then(response => {
        localStorage.setItem('token', `Bearer ${response.data.access_token}`);
        resolve(response.data.access_token);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const register = (data) => new Promise((resolve, reject) => {
    axios.post(`${API_URL}/register`, data)
    .then(response => {
        resolve(response.status);
    }).catch(error => {
        reject(error.response.data);
    });
});

export const logout = () => new Promise((resolve, reject) => {
    localStorage.removeItem('token');
    resolve(true)
    .catch(error => {
        reject(error);
    });
});