import React, {useState} from 'react';
import UserDetails from './UserDetails';
import Button from '@mui/material/Button';
import axios from 'axios';
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import CancelIcon from '@mui/icons-material/Cancel';
import IconButton from '@mui/material/IconButton';
import FavoriteIcon from '@mui/icons-material/Favorite';
import Box from "@mui/material/Box";
import blankProfile from '../images/blankProfile.jpg';

import '../styles/UserPage.scss';

type UserPageProps = {
    login: String,
    logOut: any,
    user: any
}

type PossibleMatch = {
    userId: string,
    name: string,
    age: number,
    description: string,
    interests: string,
    image: string,
}

const UserPage = ({login, logOut, user}: UserPageProps) => {
    const [users, setUsers] = useState<any[]>([]);
    const [possibleMatches, setPossibleMatches] = useState<PossibleMatch[]>([]);
    const [matchesLoading, setMatchesLoading] = useState(false);
    const [matchCounter, setMatchCounter] = useState(0);
    const [showMatches, setShowMatches] = useState(false);
    const [myMatches, setMyMatches] = useState<PossibleMatch[]>([]);
    const [myMatchesLoading, setMyMatchesLoading] = useState(false);

    const handleGetPossibleMatches = async () => {
        const API_URL = `http://localhost:8080/display/getPotentialMatches/${user.id}`
        setMatchesLoading(true);
        setShowMatches(false);
        setMatchCounter(0);
        try {
            setPossibleMatches([]);
            const res = await axios.get(API_URL)
            setPossibleMatches(res.data)
            console.log(res.data);
        } catch (e) {
            alert("SET PREFERENCES!")
            console.log(e)
        } finally {
            setMatchesLoading(false)
        }
    }

    const getMatches = async ()  => {
        const API_URL = `http://localhost:8080/display/getMatches/${user.id}`
        setShowMatches(true);
        setMyMatchesLoading(true);
        try{
            const res = await axios.get(API_URL)
            setMyMatches(res.data);
            console.log(res.data);
        } catch(e){
            console.log(e)
        } finally{
            setMyMatchesLoading(false);
        }

    }

    console.log(`possible matches: ${JSON.stringify(possibleMatches[0])}`);
    const handleLogOut = () => {
        logOut();
    }
    // const firstMatch = possibleMatches ? possibleMatches[0] : null;
    // // possibleMatches.map( match => {...  })

    const handleNextMatch = () => setMatchCounter((prev) => prev + 1)
// localhost:8080/api//updateLikedUser/2?liked_user_id=1
    const handleAddMatch = async (matchId) =>{
        const URL = `http://localhost:8080/api/updateLikedUser/${user.id}?liked_user_id=${matchId}`
        try{
            await axios.patch(URL);
        }catch(err){

            console.log(err);
        }
        handleNextMatch();
    }

    const matchDetails = (possibleMatch: PossibleMatch) =>{ return (   possibleMatch ? (<Card sx={{ maxWidth: 345 }}>
        <CardMedia
            component="img"
            height="250"
            image={possibleMatch.image === "" ? blankProfile : `data:image/jpeg;base64,${possibleMatch.image}`}
            alt="awesome match"
        />
        <CardContent>
            <Typography gutterBottom variant="h5" component="div">
                {`${possibleMatch.name}  ${possibleMatch.age}`}
            </Typography>
            <Typography variant="body1" color="text.secondary">
                {possibleMatch.description}
            </Typography>
            <Typography variant="body2" color="text.secondary">
                Interests: {possibleMatch.interests}
            </Typography>
        </CardContent>
        <CardActions sx={{justifyContent: 'space-between'}}>
            <IconButton color="secondary" onClick={handleNextMatch}>
                <CancelIcon />
            </IconButton>
            <IconButton color="secondary" onClick={() => handleAddMatch(possibleMatch.userId)}>
                <FavoriteIcon />
            </IconButton>
        </CardActions>
    </Card>) : <p>Out of matches..</p>)}


    const renderMatch = (match) => {
        return (
            <Card sx={{ display: "flex" }}>
            <Box sx={{ display: "flex", flexDirection: "column" }}>
                <CardContent sx={{ flex: "1 0 auto" }}>
                    <Typography component="div" variant="h5">
                        {`${match.name} ${match.age}`}
                    </Typography>
                    <Typography
                        variant='subtitle2'
                        color="text.secondary"
                    >
                        {match.description}
                    </Typography>
                    <Typography
                        variant='subtitle2'
                        color="text.secondary"
                    >
                      Zainteresowania:  {match.interests}
                    </Typography>
                </CardContent>
            </Box>
                {match.image ?  (<CardMedia
                component="img"
                sx={{ width: 150 }}
                image={match.image === "" ? blankProfile : `data:image/jpeg;base64,${match.image}`}
            />) : <p>No photo</p>}
        </Card>)
    }

    return (
        <div className="userPage">

            <button onClick={handleLogOut}>Wyloguj</button>
            {user && <h1>{`Witaj ${user.name.charAt(0).toUpperCase() + user.name.slice(1)}`}</h1>}
            <main>
                <div>
                    <Button color="primary" variant="contained" onClick={handleGetPossibleMatches}>START MACZ</Button>
                    <Button color="primary" variant="contained" onClick={getMatches}>SHOW MACZ</Button>
                </div>

                <UserDetails user={user} refresh={() => null}/>
                {matchesLoading && <p>Loading...</p>}
                {!showMatches && possibleMatches.length === 0 && <p>Matches not found...</p>}
                {!showMatches && possibleMatches.length > 0 && matchCounter > possibleMatches.length-1 && (<p>Out of matches...</p>) }
                {!showMatches && possibleMatches.length > 0 && matchDetails(possibleMatches[matchCounter])}
                {/*{showMatches && setMyMatchesLoading && <p>Loading matches..</p>}*/}
                {showMatches && myMatches && (<Box>{myMatches.map(match => renderMatch(match))}</Box>)}
            </main>


        </div>
    );
}

export default UserPage;
