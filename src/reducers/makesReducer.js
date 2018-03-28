import { SET_MAKES, SET_ERROR } from "../actions/makes";

const makesReducer = (state = { makes : [], status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_MAKES : 
            return {
                ...state,
                makes : action.makes
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default makesReducer;