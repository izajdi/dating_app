
import React, {useState,useEffect} from 'react';
import UserPage from './UserPage'
import Login from './Login'



// var passwordHash = require('password-hash');



const LoginPage = () => {

    
    const [login,setLogin] = useState("");
    // const [password,setPassword] = useState("");
    const [isLogged,setIsLogged] = useState(false);
    const [currentUser, setCurrentUser] = useState('');


    const strzelDoApi = (requestOptions) => {
        fetch('http://localhost:8080/api/login', requestOptions)
            .then(response => {
                if(response.status === 200){
                    handleOnLoginChange('');
                    sessionStorage.setItem('login', login)
                    return response.json();
                }else if(response.status===401){
                    alert("Nieprawidłowe dane!")
                }
            }).then(res => setCurrentUser(res))
            .catch(err => console.log(err));


    }

    // const handleOnLoginChange = (e:React.FormEvent<HTMLInputElement>) => setLogin(e.currentTarget.value);
    // const handleOnPassChange = (e:React.FormEvent<HTMLInputElement>) => setPassword(e.currentTarget.value);
    const handleOnLoginChange = (prev) => {
        setLogin(prev);
        setIsLogged(true);
    }
    const handleOnLogOut = () => {
      setIsLogged(false);
      sessionStorage.clear();
    }


    useEffect(() => {
      const loggedIn = sessionStorage.getItem("login");
      console.log(loggedIn);
      if (loggedIn) {
        
        setLogin(loggedIn);
        setIsLogged(true);
      }
    }, []);

    console.log(login);

  return (
    isLogged ? <UserPage user={currentUser} login={login} logOut={handleOnLogOut} /> : <Login  handleLogin={strzelDoApi} logIn={handleOnLoginChange}/>
    
  );
}

export default LoginPage;
