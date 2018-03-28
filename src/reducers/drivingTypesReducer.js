import { SET_DRIVING_TYPES, SET_ERROR } from "../actions/drivingTypes";

const drivingTypesReducer = (state = { drivingTypes : [], status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_DRIVING_TYPES : 
            return {
                ...state,
                drivingTypes : action.drivingTypes
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default drivingTypesReducer;