import { SET_MODELS, SET_ERROR } from "../actions/models";

const modelsReducer = (state = { models : [], status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_MODELS : 
            return {
                ...state,
                models : action.models
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default modelsReducer;