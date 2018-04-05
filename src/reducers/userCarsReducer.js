import { SET_USER_CARS, ADD_USER_CAR, SET_ERROR } from "../actions/userCars";

const userCarsReducer = (state = { userCars: {}, userCar: {}, status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_USER_CARS : 
            return {
                ...state,
                userCars : action.userCars
            };

        case ADD_USER_CAR : 
            return {
                ...state,
                userCar : action.userCar
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