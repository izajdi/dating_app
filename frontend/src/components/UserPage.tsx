import React, {useState, useEffect} from 'react';

import '../styles/App.css';


type UserPageProps = {
  login: String,
  logOut:any
}

const UserPage = ({login,logOut}:UserPageProps) => {


  const [users,setUsers] = useState([]);

  /*
  useEffect pobrac wszytskich userow z bazy i przefiltorwac, wykluczyc zalogowanego usera po loginie 
  a na podstawie obiektu danego usera wyrenderowac odpowiedni wyglad strony


  */

  const handleLogOut = () => {
    logOut();
  }


  return (
    <div>
      
      <h1>UserPage</h1>
      <button onClick={handleLogOut}>Wyloguj</button>
      
      </div>
  );
}

export default UserPage;
