import React,{useState} from 'react';
import UserDetailsFormProps from './Profile';
import '../styles/UserDetails.scss'

import blankProfile from '../images/blankProfile.jpg';
import UserDetailsForm from './UserDetailsForm';

var passwordHash = require('md5');

type UserDetailsProps = {
    user: any,
    refresh:any
    
  }


const UserDetails = ({user,refresh}:UserDetailsProps) => {

  const [editMode,setEditMode] = useState(false);
  
    

  const handleOnClick = ()=>{
    setEditMode(prev=>!prev);
  }


  

  const userDetails = <>
                <img src={blankProfile} alt="" />

                <p>Imie: <span>{user && user.name}</span></p>
                <p>Email: <span>{user && user.email}</span></p>
                <p>Data urodzenia: <span>{user && user.dateOfBirthday}</span></p>
                <p>Panstwo: <span>{user && user.country}</span></p>
                <p>Miasto: <span>{user && user.city}</span></p>
                <p>Opis: <span>{user && user.description}</span></p>
                      </>

 
const userDetailsForm = user &&<UserDetailsForm
      userId={user.id}
      name={user.name}
      email={user.email}
      dateOfBirthday={user.dateOfBirthday}
      country={user.country}
        city={user.city}
        description={user.description}
        password={user.password}

        refresh={refresh}

        changeUser={()=>setEditMode(false)}

      
/>


  return (
    <div className="userDetails" >
        <button className="disable" onClick={handleOnClick} >Edytuj</button>

       { editMode ? userDetailsForm : userDetails}
    </div>
  );
}

export default UserDetails;
