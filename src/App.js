import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import MainPage from './containers/MainPage';
import Navbar from './components/Navbar';
import LoginPage from './containers/LoginPage';

class App extends Component {
  render() {
    return (
      <div>
        <Navbar/>
        <div className = 'ui container'>
          <Switch>
            <Route exact path = '/' component = {MainPage}/>
            <Route exact path = '/login' component = {LoginPage}/>
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;