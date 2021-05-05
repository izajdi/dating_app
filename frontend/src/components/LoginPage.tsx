
import React, {useState} from 'react';
import UserPage from './UserPage'
import Login from './Login'

import '../styles/App.css';

// var passwordHash = require('password-hash');



const LoginPage = () => {

    
    const [login,setLogin] = useState("");
    // const [password,setPassword] = useState("");
    const [isLogged,setIsLogged] = useState(false);

    // const handleOnLoginChange = (e:React.FormEvent<HTMLInputElement>) => setLogin(e.currentTarget.value);
    // const handleOnPassChange = (e:React.FormEvent<HTMLInputElement>) => setPassword(e.currentTarget.value);
    const handleOnLoginChange = (prev) => {
        setLogin(prev);
        setIsLogged(true);
    }
    const handleOnLogOut = () => setIsLogged(false);

  return (
    isLogged ? <UserPage login={login} logOut={handleOnLogOut} /> : <Login   logIn={handleOnLoginChange}/>
    
  );
}

export default LoginPage;
