import { SET_FUEL_ECONOMY_AVGS, SET_ERROR } from "../actions/fuelEconomyAvgs";

const fuelEconomyAvgsReducer = (state = { fuelEconomyAvgs : [], status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_FUEL_ECONOMY_AVGS : 
            return {
                ...state,
                fuelEconomyAvgs : action.fuelEconomyAvgs
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default fuelEconomyAvgsReducer;