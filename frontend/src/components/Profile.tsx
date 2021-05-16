import React from 'react';

import '../styles/Profile.scss';

import blankProfile from '../images/blankProfile.jpg';

type ProfileProps = {
    profile: any,
    
  }

const Profile = ({profile} : ProfileProps) => {

    const calculateAge = (birthday) => { // birthday is a date
       
        var birthDate = new Date(birthday);
        var ageDifMs = Date.now() - birthDate.getTime();
        var ageDate = new Date(ageDifMs); // miliseconds from epoch
        return Math.abs(ageDate.getUTCFullYear() - 1970);
    }

    const age = profile ? calculateAge(profile.dateOfBirthday)  : null;

  return (
    <li>{`${profile.name} : ${age} : ${profile.city}  : ${profile.description}` } <img src={blankProfile} alt="" /></li>
  );
}

export default Profile;
