import React, {useState, useEffect} from 'react';
import UserDetails from './UserDetails';
import FilterForm from './FilterForm';
import ProfilesList from './ProfilesList';
import Button from '@mui/material/Button';
import axios from 'axios';


import '../styles/UserPage.scss';

type UserPageProps = {
  login: String,
  logOut:any,
    user:any
}

const UserPage = ({login,logOut, user}:UserPageProps) => {
    console.log(user);
  const [isFiltered,setIsFiltered] = useState(false);
  const [users,setUsers] = useState<any[]>([]);
  const [profilesToDisplay,setProfilesToDisplay] = useState<any[]>([]);
  const currentUser = users.filter( user => user.email === login)[0];
  const [possibleMatches, setPossibleMatches] = useState([]);
  const [matchesLoading, setMatchesLoading] = useState(false);


  const handleGetPossibleMatches =  async () =>{
      const API_URL = `http://localhost:8080/preferences/getPossibleMatch/${user.id}`
      setMatchesLoading(true);
      try{
          const res = await axios.get(API_URL)
          setPossibleMatches(res.data)
          console.log(res.data);
      } catch(e){
          console.log(e)
      }finally {
          setMatchesLoading(false)
      }
  }

  const handleLogOut = () => {
    logOut();
  }


  return (
    <div className="userPage">

        <button onClick={handleLogOut}>Wyloguj</button>
      {user && <h1>{`Witaj ${user.name.charAt(0).toUpperCase() + user.name.slice(1)}`}</h1>}
      <main>
          <Button color="primary" variant="contained" onClick={handleGetPossibleMatches}>START MACZ</Button>
      <UserDetails user={user} refresh={() => null} />
          {matchesLoading && <p>Loading...</p>}
      </main>

      
      </div>
  );
}

export default UserPage;
