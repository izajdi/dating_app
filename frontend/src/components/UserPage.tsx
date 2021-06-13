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

  const [isFiltered,setIsFiltered] = useState(false);
  const [users,setUsers] = useState<any[]>([]);
  const [profilesToDisplay,setProfilesToDisplay] = useState<any[]>([]);
  const currentUser = users.filter( user => user.email === login)[0];

  const [refresh,setRefresh] = useState(false);

  const handleRefresh = ()=>{
    setProfilesToDisplay(users);
  }

 const getCurrentUsers = ()=>{
  setRefresh(prev=>!prev);
  fetch('http://localhost:8080/api/users')
  .then(response => response.json())
  .then(data =>{
    setUsers(data);
    setProfilesToDisplay(data);
  })
  .catch(err => console.log(err));
 }

  useEffect( () =>{
    fetch('http://localhost:8080/api/users')
        .then(response => response.json())
        .then(data =>{
          setUsers(data);
          setProfilesToDisplay(data);
        })
        .catch(err => console.log(err));
  },[])

  const handleLogOut = () => {
    logOut();
  }

  const calculateAge = (birthday) => { // birthday is a date
       
    var birthDate = new Date(birthday);
    var ageDifMs = Date.now() - birthDate.getTime();
    var ageDate = new Date(ageDifMs); // miliseconds from epoch
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

  const handleFilter = (minAge:number,maxAge:number,city:String,gender:String)=>{
    const profiles = users.filter(user => {
      const _age = calculateAge(user.dateOfBirthday);
      if(minAge <= _age && _age <= maxAge)return true;
      else return false;
    }).filter(user => user.city.toUpperCase().includes(city.toUpperCase()))
    .filter(user => {
      if(gender ==='')return true;

      return user.gender === gender
    });


    setProfilesToDisplay(profiles)
  }
 

  return (
    <div className="userPage">
      
      
      <button onClick={handleLogOut}>Wyloguj</button>
      {currentUser && <h1>{`Witaj ${currentUser.name.charAt(0).toUpperCase() + currentUser.name.slice(1)}`}</h1>}
      <main>
      
      <FilterForm  handleFilter={handleFilter} refresh={handleRefresh}/>
      <ProfilesList users={profilesToDisplay}  currentUser={currentUser}/>
      <UserDetails user={currentUser} refresh={getCurrentUsers} />
      </main>
      
      
      </div>
  );
}

export default UserPage;
