import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHeart, faHeartBroken} from '@fortawesome/free-solid-svg-icons'

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
    <li className="listItem">
       <img src={blankProfile} alt="" /> <br/>
      {`${profile.name} : ${age} : ${profile.gender}: ${profile.city}  : ${profile.description}` }
      <br/>
      
      
       <button className="dislike"><FontAwesomeIcon icon={faHeartBroken} /></button>
       <button className="like"><FontAwesomeIcon icon={faHeart} /></button>

      
      </li>
  );
}

export default Profile;
