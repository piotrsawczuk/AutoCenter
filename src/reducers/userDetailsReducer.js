import { SET_USER_DETAILS, SET_ERROR } from "../actions/userDetails";

const userDetailsReducer = (state = { userDetails : {}, status : 0 }, action = {}) => {

    switch (action.type) {

        case SET_USER_DETAILS : 
            return {
                ...state,
                userDetails : action.userDetails
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default userDetailsReducer;