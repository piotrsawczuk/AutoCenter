import { SET_TRIM, SET_ERROR } from "../actions/trim";

const trimReducer = (state = { trim: {}, status: 0 }, action = {}) => {
    switch (action.type) {

        case SET_TRIM : 
            return {
                ...state,
                trim: action.trim
            };

        case SET_ERROR :
            return {
                ...state,
                error: action.error
            };

        default : return state;
    }
}

export default trimReducer;