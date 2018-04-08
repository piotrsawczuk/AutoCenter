import { combineReducers } from 'redux';
import authReducer from './authReducer';
import yearsReducer from './yearsReducer';
import makesReducer from './makesReducer';
import modelsReducer from './modelsReducer';
import trimsReducer from './trimsReducer';
import trimReducer from './trimReducer';
import drivingTypesReducer from './drivingTypesReducer';
import fuelTypesReducer from './fuelTypesReducer';
import exploitationTypesReducer from './exploitationTypesReducer';
import fuelEconomyAvgsReducer from './fuelEconomyAvgsReducer';
import repairsTotalCostsReducer from './repairsTotalCostsReducer';
import userCarsReducer from './userCarsReducer';
import userCarDetailsReducer from './userCarDetailsReducer';

export default combineReducers({
    authReducer,
    yearsReducer,
    makesReducer,
    modelsReducer,
    trimsReducer,
    trimReducer,
    drivingTypesReducer,
    fuelTypesReducer,
    exploitationTypesReducer,
    fuelEconomyAvgsReducer,
    repairsTotalCostsReducer,
    userCarsReducer,
    userCarDetailsReducer
});