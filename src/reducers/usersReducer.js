import { SET_ERROR } from "../actions/users";

const usersReducer = (state = { status: 0 }, action = {}) => {
    switch (action.type) {

        case SET_ERROR :
            return {
                ...state,
                error: action.error
            };

        default : return state;
    }
}

export default usersReducer;