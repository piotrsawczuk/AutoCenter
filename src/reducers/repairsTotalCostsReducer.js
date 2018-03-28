import { SET_REPAIRS_TOTAL_COSTS, SET_ERROR } from "../actions/repairsTotalCosts";

const repairsTotalCostsReducer = (state = { repairsTotalCosts : [], status : 0 }, action = {}) => {
    switch (action.type) {

        case SET_REPAIRS_TOTAL_COSTS :
            return {
                ...state,
                repairsTotalCosts : action.repairsTotalCosts
            };

        case SET_ERROR :
            return {
                ...state,
                error : action.error
            };

        default : return state;
    }
}

export default repairsTotalCostsReducer;