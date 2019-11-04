import { SET_FUEL_TYPES, SET_ERROR } from "../actions/fuelTypes";

const fuelTypesReducer = (state = { fuelTypes: [], status: 0 }, action = {}) => {
    switch (action.type) {

        case SET_FUEL_TYPES : 
            return {
                ...state,
                fuelTypes: action.fuelTypes
            };

        case SET_ERROR :
            return {
                ...state,
                error: action.error
            };

        default : return state;
    }
}

export default fuelTypesReducer;