import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Link } from 'react-router-dom';
import { Breadcrumb, Button, Message } from 'semantic-ui-react';
import YearSelection from '../components/car/YearSelection';
import MakeSelection from '../components/car/MakeSelection';
import ModelSelection from '../components/car/ModelSelection';
import TrimSelection from '../components/car/TrimSelection';
import UserCarDetailsForm from '../components/car/UserCarDetailsForm';
import { findOne as findYears } from '../actions/years';
import { findAll as findAllMakes } from '../actions/makes';
import { findAll as findAllModels } from '../actions/models';
import { findAll as findAllTrims} from '../actions/trims';
import { findOne as findTrim} from '../actions/trim';
import { save as addUserCar} from '../actions/userCars';
import { save as addUserCarDetails} from '../actions/userCarDetails';


class AddUserCarPage extends Component {
    
    state = {
        disabledMakes: true,
        disabledModels: true,
        disabledTrims: true,
        visibleButton: false,
        currentYear: '',
        currentMake: '',
        currentModel: '',
        isCarAdded: false,
        redirectToCarsPage: false
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
            this.setState({ visibleButton: true });
        }
    }

    addCar = () => {
        this.props.addUserCar(
            { 
                carApiId: this.props.trim.model_id,
                carName: `${this.props.trim.model_year} ${this.props.trim.make_display} ${this.props.trim.model_name} ${this.props.trim.model_trim}`
            }
        )
        this.setState({ isCarAdded: true });
    }

    addCarDetails = (data) => {
        if (this.props.userCar) {
            this.props.addUserCarDetails(this.props.userCar.id, data);
            this.setState({ redirectToCarsPage: true });
        }
    }

    render() {
        return (
            !this.props.isAuthenticated ?
                <div>
                    <Redirect to='/'/>
                </div>
            :
                this.state.redirectToCarsPage ?
                    <div>
                        <Redirect to='/cars'/>
                    </div>
                :
                    <div>
                        <div style={{marginBottom: '30px'}}>
                            <Breadcrumb size='big'>
                                <Breadcrumb.Section><Link to="/cars">Back to cars</Link></Breadcrumb.Section>
                                <Breadcrumb.Divider icon='right chevron' />
                                <Breadcrumb.Section active>Add car</Breadcrumb.Section>
                            </Breadcrumb>
                        </div>
                        <div>
                            <div style={{marginBottom: '10px'}}>
                                <YearSelection minYear = {this.props.years.min_year} maxYear = {this.props.years.max_year} onChange = {this.onChangeYearSelection} />
                            </div>
                            <div style={{marginBottom: '10px'}}>
                                <MakeSelection makes = {this.props.makes} isDisabled = {this.state.disabledMakes} onChange = {this.onChangeMakeSelection} />
                            </div>
                            <div style={{marginBottom: '10px'}}>
                                <ModelSelection models = {this.props.models} isDisabled = {this.state.disabledModels} onChange = {this.onChangeModelSelection}/>
                            </div>
                            <div style={{marginBottom: '10px'}}>
                                <TrimSelection trims = {this.props.trims} isDisabled = {this.state.disabledTrims} onChange = {this.onChangeTrimSelection}/>
                            </div>
                            <div style={{marginBottom: '70px'}}>
                                {this.state.visibleButton &&  <Button className="ui large primary left floated button" disabled={this.state.isCarAdded} onClick={() => this.addCar()}>Add car</Button>  }
                            </div>
                            <div style={{marginBottom: '50px'}}>
                                {this.state.isCarAdded && <Message success={true} header="Success!" content='Your car was successfully added'/> }
                            </div>
                            <div style={{marginBottom: '70px'}}>
                                {this.state.isCarAdded &&<UserCarDetailsForm onSubmit = {this.addCarDetails.bind(this)} /> }
                            </div>
                        </div>
                    </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isAuthenticated: state.authReducer.isAuthenticated,
        years: state.yearsReducer.years,
        makes: state.makesReducer.makes,
        models: state.modelsReducer.models,
        trims: state.trimsReducer.trims,
        trim: state.trimReducer.trim,
        userCar: state.userCarsReducer.userCar
    }
}

export default connect(mapStateToProps,
    {
        findYears,
        findAllMakes,
        findAllModels,
        findAllTrims,
        findTrim,
        addUserCar,
        addUserCarDetails
    }
) (AddUserCarPage);