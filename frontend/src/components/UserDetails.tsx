import React,{useState} from 'react';
import axios from 'axios';
import '../styles/UserDetails.scss'

import blankProfile from '../images/blankProfile.jpg';
import UserDetailsForm from './UserDetailsForm';

var passwordHash = require('md5');
var FormData = require('form-data');

type UserDetailsProps = {
    user: any,
    refresh:any
    
  }


const UserDetails = ({user,refresh}:UserDetailsProps) => {

  const [editMode,setEditMode] = useState(false);
  const [photo,setPhoto] = useState(undefined as any);
  const [isPhotoFormActive,setIsPhotoFormActive] = useState(false);


  
    

  const handleOnClick = ()=>{
    setEditMode(prev=>!prev);
  }

  const handleChangePhoto = (e)=>{
     setPhoto(e.currentTarget.files[0]);
  }

  const handleChangePhotoForm = ()=>{
    setIsPhotoFormActive(true);
  }

  const handleSubmit = (e)=>{
    e.preventDefault();
    setIsPhotoFormActive(false);

  let form_data = new FormData();
  form_data.append('value', photo, photo.name);
  form_data.append('userId', user.id);
 
  let url = 'http://localhost:8000/api/addPhoto/';


  axios.post(url, form_data, {
    headers: {
      'content-type': 'multipart/form-data'
    }
  })
      .then(res => {
        console.log(res.data);
      })
      .catch(err => console.log(err))


  setIsPhotoFormActive(false);
  setPhoto(undefined as any);
  }




  
  const handleClearPhotoForm = (e)=>{
    // e.preventDefault();
    setIsPhotoFormActive(false);
    setPhoto(undefined as any);

    
  }

  const photoForm = <>
                    <form onSubmit={handleSubmit}>
                      <input type="file" name="picture"  onChange={handleChangePhoto} />
                      <button onClick={handleClearPhotoForm}>Anuluj</button>
                      <button>Zmien</button>
                    </form>
                    </>

  const userDetails = <>
                <img src={blankProfile} alt="" onClick={handleChangePhotoForm}/>
                {isPhotoFormActive && photoForm}

                <p>Imie: <span>{user && user.name}</span></p>
                <p>Email: <span>{user && user.email}</span></p>
                <p>Data urodzenia: <span>{user && user.dateOfBirthday}</span></p>
                <p>Panstwo: <span>{user && user.country}</span></p>
                <p>Miasto: <span>{user && user.city}</span></p>
                <p>Pleć: <span>{user && user.gender}</span></p>
                <p>Opis: <span>{user && user.description}</span></p>
                      </>

 
const userDetailsForm = user &&<UserDetailsForm
      userId={user.id}
      name={user.name}
      email={user.email}
      dateOfBirthday={user.dateOfBirthday}
      country={user.country}
        city={user.city}
        gender={user.gender}
        description={user.description}
        password={user.password}

        refresh={refresh}

        changeUser={()=>setEditMode(false)}

      
/>


  return (
    <div className="userDetails" >
        <button className="disable" onClick={handleOnClick} >{editMode ? "Anuluj":"Edytuj"}</button>

       { editMode ? userDetailsForm : userDetails}
    </div>
  );
}

export default UserDetails;
