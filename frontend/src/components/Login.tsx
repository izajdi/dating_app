
import React, {useState} from 'react';


import '../styles/App.css';

var passwordHash = require('password-hash');

type UserPageLoginProps = {
  logIn:any
}

const Login = ({logIn}:UserPageLoginProps) => {

    
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
        email:login,
        password:passwordHash.generate(password)

      }

      console.log(JSON.stringify(loginCredentials));
      

      // if zalogowal sie to wywolujemy metode logIn i zmieniamy state dla LoginPage -> przenosimy sie do strony UserPage
      // logIn(login);
      // else robimy return i wyswietlamy alert o bledzie logowania

      const requestOptions = {
        method: 'POST',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
        body: JSON.stringify(loginCredentials) 
    };

    fetch('http://localhost:8080/api/login', requestOptions)
    .then(response => response.json())
    .then(data =>console.log(data))
    .catch(err => console.log(err));



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
