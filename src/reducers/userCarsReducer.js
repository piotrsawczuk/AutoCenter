import { SET_USER_CARS, SET_ERROR } from "../actions/userCars";

const userCarsReducer = (state = { userCars : {}, status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_USER_CARS : 
            return {
                ...state,
                userCars : action.userCars
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default userCarsReducer;