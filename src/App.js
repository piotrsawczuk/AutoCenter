import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import MainPage from './containers/MainPage';
import LoginPage from './containers/LoginPage';
import LogoutPage from './containers/LogoutPage';
import SignUpPage from './containers/SignUpPage';

class App extends Component {
  render() {
    return (
      <div>
        <Navbar/>
        <div className = 'ui container'>
          <Switch>
            <Route exact path = '/' component = {MainPage}/>
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