import React, { Component } from 'react';
import { connect } from 'react-redux';
import YearSelection from '../components/car/YearSelection';
import MakeSelection from '../components/car/MakeSelection';
import ModelSelection from '../components/car/ModelSelection';
import TrimSelection from '../components/car/TrimSelection';
import CarDataTable from '../components/car/CarDataTable';
import FuelEconomyAvgTable from '../components/fuelEconomy/FuelEconomyAvgTable';
import RepairsTotalCostsTable from '../components/repair/RepairsTotalCostsTable';
import { findOne as findYears } from '../actions/years';
import { findAll as findAllMakes } from '../actions/makes';
import { findAll as findAllModels } from '../actions/models';
import { findAll as findAllTrims} from '../actions/trims';
import { findOne as findTrim} from '../actions/trim';
import { findAll as findFuelEconomyAvgs} from '../actions/fuelEconomyAvgs';
import { findAll as findRepairsTotalCosts} from '../actions/repairsTotalCosts';


class MainPage extends Component {
    state = {
        disabledMakes: true,
        disabledModels: true,
        disabledTrims: true,
        visibleTables: false,
        currentYear: '',
        currentMake: '',
        currentModel: ''
    }

    componentDidMount = () => {
        this.props.findYears();
    }

    onChangeYearSelection = (e, year) => {
        if (year.value) {
            this.setState(
                { 
                    disabledMakes: false,
                    currentYear: year.value
                }
            );
            this.props.findAllMakes(year.value);
        }
    }

    onChangeMakeSelection = (e, make) => {
        if (make.value && this.state.currentYear) {
            this.setState(
                { 
                    disabledModels: false,
                    currentMake: make.value
                }
            );
            this.props.findAllModels(this.state.currentYear, make.value);
        }
    }

    onChangeModelSelection = (e, model) => {
        if (model.value && this.state.currentYear && this.state.currentMake) {
            this.setState(
                { 
                    disabledTrims: false,
                    currentModel: model.value
                }
            );
            this.props.findAllTrims(this.state.currentYear, this.state.currentMake, model.value);
        }
    }

    onChangeTrimSelection = (e, trim) => {
        if (trim.value) {
            this.props.findTrim(trim.value);
            this.props.findFuelEconomyAvgs(trim.value);
            this.props.findRepairsTotalCosts(trim.value);
            this.setState(
                { 
                    visibleTables: true
                }
            );
        }
    }

    render() {
        return (
            <div>
                <div>
                    <YearSelection minYear = {this.props.years.min_year} maxYear = {this.props.years.max_year} onChange = {this.onChangeYearSelection} />
                    <MakeSelection makes = {this.props.makes} isDisabled = {this.state.disabledMakes} onChange = {this.onChangeMakeSelection} />
                    <ModelSelection models = {this.props.models} isDisabled = {this.state.disabledModels} onChange = {this.onChangeModelSelection}/>
                    <TrimSelection trims = {this.props.trims} isDisabled = {this.state.disabledTrims} onChange = {this.onChangeTrimSelection}/>
                </div>
                <div>
                    {this.state.visibleTables && <FuelEconomyAvgTable fuelEconomyAvgs = {this.props.fuelEconomyAvgs} />}
                </div>
                <div>
                    {this.state.visibleTables && <RepairsTotalCostsTable repairsTotalCosts = {this.props.repairsTotalCosts} />}
                </div>
                <div>
                    {this.state.visibleTables && <CarDataTable trim = {this.props.trim} />}
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        years: state.yearsReducer.years,
        makes: state.makesReducer.makes,
        models: state.modelsReducer.models,
        trims: state.trimsReducer.trims,
        trim: state.trimReducer.trim,
        fuelEconomyAvgs: state.fuelEconomyAvgsReducer.fuelEconomyAvgs,
        repairsTotalCosts: state.repairsTotalCostsReducer.repairsTotalCosts
    }
}

export default connect(mapStateToProps,
    {
        findYears,
        findAllMakes,
        findAllModels,
        findAllTrims,
        findTrim,
        findFuelEconomyAvgs,
        findRepairsTotalCosts
    }
) (MainPage);