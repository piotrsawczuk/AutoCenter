import React, { Component } from 'react';

class App extends Component {
  render() {
    return (
      <div>
        <Navbar/>
        <div className = 'ui container'>
          <Switch>
            <Route exact path = '/' component = {MainPage}/>
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
