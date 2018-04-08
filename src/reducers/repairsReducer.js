import { SET_REPAIRS, SET_REPAIRS_TOTAL_COSTS, ADD_REPAIR, SET_ERROR } from "../actions/repairs";

const repairsReducer = (state = { repairs: {}, repairsTotalCosts: {}, repair: {}, status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_REPAIRS : 
            return {
                ...state,
                repairs : action.repairs
            };

        case SET_REPAIRS_TOTAL_COSTS : 
            return {
                ...state,
                repairsTotalCosts : action.repairsTotalCosts
            };

        case ADD_REPAIR : 
            return {
                ...state,
                repair : action.repair
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default repairsReducer;