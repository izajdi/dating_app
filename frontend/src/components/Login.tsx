
import React, {useState} from 'react';

import '../styles/App.css';

const Login = () => {

    
    const [login,setLogin] = useState("");
    const [password,setPassword] = useState("");

    const handleLogIn = (e)=>{
        e.preventDefault();
    }

  return (
    <div className="loginPage">
        <form>
        <label htmlFor="login">
                Login:
                <input id="login" value={login} type="text"/>
            </label>    
            <label htmlFor="password">
                Hasło:
                <input id="password" value={password} type="password"/>
            </label> 

            <button onClick={handleLogIn} >Zaloguj!</button>

            </form>
    </div>
  );
}

export default Login;
