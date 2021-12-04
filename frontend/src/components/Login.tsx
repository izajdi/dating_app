
import React, {useState} from 'react';

import '../styles/Login.scss';


// var passwordHash = require('password-hash');
var passwordHash = require('md5');



type UserPageLoginProps = {
  logIn:any,
    handleLogin: (user) => void,
}

const Login: React.FC<UserPageLoginProps> = ({logIn, handleLogin}) => {

    
    const [login,setLogin] = useState("");
    const [password,setPassword] = useState("");
    const [user, setUser] = useState('');

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
            name:"",
            dateOfBirthday:"", 
            email:login,
            country:"",
            city:"",
            description:"",
            password:password,
      }

      // if zalogowal sie to wywolujemy metode logIn i zmieniamy state dla LoginPage -> przenosimy sie do strony UserPage
      // logIn(login);
      // else robimy return i wyswietlamy alert o bledzie logowania

      const requestOptions = {
        method: 'POST',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
        body: JSON.stringify(loginCredentials) 
    };

        handleLogin(requestOptions);

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
