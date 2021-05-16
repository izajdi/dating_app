import React from 'react';

import '../styles/UserDetails.scss'

import blankProfile from '../images/blankProfile.jpg';

type UserDetailsProps = {
    user: any,
    
  }


const UserDetails = ({user}:UserDetailsProps) => {


    





  return (
    <div className="userDetails" >
        <img src={blankProfile} alt="" />

        <p>Imie: <span>{user && user.name}</span></p>
        <p>Email: <span>{user && user.email}</span></p>
        <p>Data urodzenia: <span>{user && user.dateOfBirthday}</span></p>
        <p>Panstwo: <span>{user && user.country}</span></p>
        <p>Miasto: <span>{user && user.city}</span></p>
        <p>Opis: <span>{user && user.description}</span></p>
    </div>
  );
}

export default UserDetails;
