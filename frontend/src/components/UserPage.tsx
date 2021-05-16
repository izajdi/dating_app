import React, {useState, useEffect} from 'react';
import UserDetails from './UserDetails';
import FilterForm from './FilterForm';
import ProfilesList from './ProfilesList';


import '../styles/UserPage.scss';

type UserPageProps = {
  login: String,
  logOut:any
}

const UserPage = ({login,logOut}:UserPageProps) => {


  const [users,setUsers] = useState<any[]>([]);
  const currentUser = users.filter( user => user.email === login)[0];

 

  useEffect( () =>{
    fetch('http://localhost:8080/api/users')
        .then(response => response.json())
        .then(data =>setUsers(data))
        .catch(err => console.log(err));
  },[])

  const handleLogOut = () => {
    logOut();
  }
 

  return (
    <div className="userPage">
      
      {currentUser && <h1>{`Witaj ${currentUser.name.charAt(0).toUpperCase() + currentUser.name.slice(1)}`}</h1>}
      <button onClick={handleLogOut}>Wyloguj</button>

      <main>
      <FilterForm/>
      <ProfilesList users={users}  currentUser={currentUser}/>
      <UserDetails user={currentUser} />
      </main>
      
      
      </div>
  );
}

export default UserPage;
