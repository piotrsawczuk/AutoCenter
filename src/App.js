import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import MainPage from './containers/MainPage';
import LoginPage from './containers/LoginPage';
import LogoutPage from './containers/LogoutPage';
import SignUpPage from './containers/SignUpPage';
import UserCarsPage from './containers/UserCarsPage';
import AddUserCarPage from './containers/AddUserCarPage';
import FuelEconomyPage from './containers/FuelEconomyPage';
import AddFuelEconomyPage from './containers/AddFuelEconomyPage';
import RepairsPage from './containers/RepairsPage';
import AddRepairPage from './containers/AddRepairPage';
import UserDetailsPage from './containers/UserDetailsPage';
import EditUserDetailsPage from './containers/EditUserDetailsPage';

class App extends Component {

  render() {
    return (
      <div>
        <Navbar/>
        <div className = 'ui container' style={{marginTop: '50px'}}>
          <Switch>
            <Route exact path = '/' component = {MainPage}/>
            <Route exact path = '/cars' component = {UserCarsPage}/>
            <Route exact path = '/cars/add' component = {AddUserCarPage}/>
            <Route exact path = '/cars/:carId/fuelEconomy' component = {FuelEconomyPage}/>
            <Route exact path = '/cars/:carId/addFuelEconomy' component = {AddFuelEconomyPage}/>
            <Route exact path = '/cars/:carId/repairs' component = {RepairsPage}/>
            <Route exact path = '/cars/:carId/addRepair' component = {AddRepairPage}/>
            <Route exact path = '/accountDetails' component = {UserDetailsPage}/>
            <Route exact path = '/accountDetails/edit' component = {EditUserDetailsPage}/>
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