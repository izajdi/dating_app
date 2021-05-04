import React from 'react';
import Login from './Login'
import Register from './Register'
import Header from './Header'
import Main from './Main'
import Footer from './Footer'


import {Switch, Route, Redirect} from "react-router-dom";


import '../styles/App.css';

const App = () => {
  return (
    <div className="App">
      <Header/>
      

            <Switch>
              <Route path="/" exact render={()=><Main/>}/>
              <Route path="/login" exact render={()=><Login/>}/>
              <Route path="/register" exact render={()=><Register/>}/>
              <Redirect from="*" to="/"/>


            </Switch>


            <Footer/>
    </div>
  );
}

export default App;