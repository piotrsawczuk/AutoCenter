import { SET_FUEL_ECONOMY_LIST, SET_FUEL_ECONOMY_AVGS, ADD_FUEL_ECONOMY, SET_ERROR } from "../actions/fuelEconomy";

const fuelEconomyReducer = (state = { fuelEconomyList: {}, fuelEconomyAvgs: {}, fuelEconomy: {}, status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_FUEL_ECONOMY_LIST : 
            return {
                ...state,
                fuelEconomyList : action.fuelEconomyList
            };

        case SET_FUEL_ECONOMY_AVGS : 
            return {
                ...state,
                fuelEconomyAvgs : action.fuelEconomyAvgs
            };

        case ADD_FUEL_ECONOMY : 
            return {
                ...state,
                fuelEconomy : action.fuelEconomy
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default fuelEconomyReducer;