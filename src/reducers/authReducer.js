import { SET_TOKEN, SET_ERROR } from "../actions/authorization";

const authReducer = (state = { token : {}, status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_TOKEN : 
            return {
                ...state,
                token : action.token
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default authReducer;