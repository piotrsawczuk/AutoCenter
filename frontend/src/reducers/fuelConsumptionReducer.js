import { SET_FUEL_ECONOMY_LIST, SET_FUEL_ECONOMY_AVGS, ADD_FUEL_ECONOMY, SET_ERROR } from "../actions/fuelConsumption";

const fuelConsumptionReducer = (state = { fuelConsumptionList: {}, fuelConsumptionAvgs: {}, fuelConsumption: {}, status: 0 }, action = {}) => {
    switch (action.type) {

        case SET_FUEL_ECONOMY_LIST : 
            return {
                ...state,
                fuelConsumptionList: action.fuelConsumptionList
            };

        case SET_FUEL_ECONOMY_AVGS : 
            return {
                ...state,
                fuelConsumptionAvgs: action.fuelConsumptionAvgs
            };

        case ADD_FUEL_ECONOMY : 
            return {
                ...state,
                fuelConsumption: action.fuelConsumption
            };

        case SET_ERROR :
            return {
                ...state,
                error: action.error
            };

        default : return state;
    }
}

export default fuelConsumptionReducer;