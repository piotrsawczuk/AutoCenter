import { SET_USER_CAR_DETAILS, SET_ERROR } from "../actions/userCarDetails";

const userCarDetailsReducer = (state = { userCarDetails : {}, status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_USER_CAR_DETAILS : 
            return {
                ...state,
                userCarDetails : action.userCarDetails
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default userCarDetailsReducer;