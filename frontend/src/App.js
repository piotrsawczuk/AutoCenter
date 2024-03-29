import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import MainPage from './containers/MainPage';
import LoginPage from './containers/LoginPage';
import LogoutPage from './containers/LogoutPage';
import SignUpPage from './containers/SignUpPage';
import CarsPage from './containers/CarsPage';
import AddCarPage from './containers/AddCarPage';
import FuelConsumptionPage from './containers/FuelConsumptionPage';
import AddFuelConsumptionPage from './containers/AddFuelConsumptionPage';
import RepairsPage from './containers/RepairsPage';
import AddRepairPage from './containers/AddRepairPage';
import UserDetailsPage from './containers/UserDetailsPage';
import EditUserDetailsPage from './containers/EditUserDetailsPage';
import PasswordEmailChangePage from './containers/PasswordEmailChangePage';
import EditCarDetailPage from './containers/EditCarDetailPage';

class App extends Component {

  render() {
    return (
      <div>
        <Navbar/>
        <div className = 'ui container' style={{marginTop: '50px'}}>
          <Switch>
            <Route exact path = '/' component = {MainPage}/>
            <Route exact path = '/cars' component = {CarsPage}/>
            <Route exact path = '/cars/add' component = {AddCarPage}/>
            <Route exact path = '/cars/:carId/edit' component = {EditCarDetailPage}/>
            <Route exact path = '/cars/:carId/fuelConsumption' component = {FuelConsumptionPage}/>
            <Route exact path = '/cars/:carId/fuelConsumption/add' component = {AddFuelConsumptionPage}/>
            <Route exact path = '/cars/:carId/repairs' component = {RepairsPage}/>
            <Route exact path = '/cars/:carId/repairs/add' component = {AddRepairPage}/>
            <Route exact path = '/accountDetails' component = {UserDetailsPage}/>
            <Route exact path = '/accountDetails/edit' component = {EditUserDetailsPage}/>
            <Route exact path = '/accountDetails/changePasswordEmail' component = {PasswordEmailChangePage}/>
            <Route exact path = '/login' component = {LoginPage}/>
            <Route exact path = '/register' component = {SignUpPage}/>
            <Route exact path = '/logout' component = {LogoutPage}/>
          </Switch>
        </div>
      </div>
    );
  }
  
}

export default App;