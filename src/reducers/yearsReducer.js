import { SET_YEARS, SET_ERROR } from "../actions/years";

const yearsReducer = (state = { years : {}, status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_YEARS : 
            return {
                ...state,
                years : action.years
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default yearsReducer;