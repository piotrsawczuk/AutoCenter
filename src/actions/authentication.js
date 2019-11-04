export const setToken = (token) => {
    return {
        type: LOGIN_SUCCESS,
        isAuthenticated: true,
        token
    }
}

export const removeToken = () => {
    return {
        type: LOGOUT_SUCCESS,
        isAuthenticated: false
    }
}

export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGOUT_SUCCESS = 'LOGOUT_SUCCESS';