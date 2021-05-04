
import React, {useState} from 'react';
import UserPage from './UserPage'

import '../styles/App.css';

var passwordHash = require('password-hash');

let isLogged = false;

const Login = () => {

    
    const [login,setLogin] = useState("");
    const [password,setPassword] = useState("");

    const handleOnLoginChange = (e:React.FormEvent<HTMLInputElement>) => setLogin(e.currentTarget.value);
    const handleOnPassChange = (e:React.FormEvent<HTMLInputElement>) => setPassword(e.currentTarget.value);

    const handleLogIn = (e)=>{
        e.preventDefault();



        if(login.length===0){
          alert("podaj login");
          return;
        }
        if(password.length===0){
          alert("podaj haslo");
          return;
        }

      const loginCredentials = {
        login,
        password:passwordHash.generate(password)

      }

      console.log(JSON.stringify(loginCredentials));
      isLogged = !isLogged;

      

    }


   

  return (
    <div className="loginPage">
    <form>
    <label htmlFor="login">
            Login:
            <input id="login" value={login} onChange={handleOnLoginChange} type="text"/>
        </label>    
        <label htmlFor="password">
            Hasło:
            <input id="password" value={password} onChange={handleOnPassChange} type="password"/>
        </label> 

        <button onClick={handleLogIn} >Zaloguj!</button>

        </form>
</div>
    
  );
}

export default Login;
