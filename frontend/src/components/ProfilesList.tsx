import React from 'react';
import Profile from './Profile';
import '../styles/ProfileList.scss'

type ProfilesListProps = {
    users: any[],
    currentUser:any
  }

const ProfilesList = ({users,currentUser}:ProfilesListProps) => {

    const profiles = users.filter(user => user.email !== currentUser.email).map(user => <Profile 
        key={user.id}
        profile={user}
    />);
    

  return (
    <div className="profilesList" >
        <ul>
            {profiles.slice(0,5)}
        </ul>
        
        
    </div>
  );
}

export default ProfilesList;
