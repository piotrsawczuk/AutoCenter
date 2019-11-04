import { SET_CARS, SET_CAR, ADD_CAR, SET_ERROR } from "../actions/cars";

const carsReducer = (state = { cars: {}, car: {}, status: 0 }, action = {}) => {
    switch (action.type) {

        case SET_CARS : 
            return {
                ...state,
                cars: action.cars
            };

        case SET_CAR : 
            return {
                ...state,
                car: action.car
            };

        case ADD_CAR : 
            return {
                ...state,
                car: action.car
            };

        case SET_ERROR :
            return {
                ...state,
                error: action.error
            };

        default : return state;
    }
}

export default carsReducer;