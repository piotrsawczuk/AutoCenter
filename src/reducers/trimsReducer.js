import { SET_TRIMS, SET_ERROR } from "../actions/trims";

const trimsReducer = (state = { trims : [], status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_TRIMS : 
            return {
                ...state,
                trims : action.trims
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default trimsReducer;