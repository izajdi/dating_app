import * as React from 'react';
import axios from 'axios';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';

type UserPreferencesPayload = {
    gender: string,
    belowAge: number,
    upperAge: number,
    interest: string,
}

interface IUserPreferences {
    userId: number,
}
const UserPreferences: React.FC<IUserPreferences> = ({userId}) => {
    const url = `http://localhost:8080/preferences/update`;
    const [gender, setGender] = React.useState('');
    const [belowAge, setBelowAge] = React.useState(0);
    const [upperAge, setUpperAge] = React.useState(0);
    const [interest, setInterest] = React.useState('');

    const handleChangeAge = (event) => {
        setGender(event.target.value);
    };
    const handleBelowAge = (event) => {
        setBelowAge(event.target.value);
    };
    const handleUpperAge = (event) => {
        setUpperAge(event.target.value);
    };

    const handleInterest = (event) => {
        setInterest(event.target.value);
    };


    const handleSubmit = async () => {
        const userPref = {
            userId,
            gender,
            belowAge,
            upperAge,
            interest
        }
        try {
            await axios.put(url, userPref)
        } catch (e) {
            return <p>Sth went wrong...</p>
        }
    }

    return (
        <Box sx={{display:'flex', flexDirection: 'column'}}>
        <form style={{display: 'flex', flexDirection:'column'}}>
            <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
                <InputLabel id="demo-simple-select-standard-label">Gender</InputLabel>
                <Select
                    labelId="demo-simple-select-standard-label"
                    id="demo-simple-select-standard"
                    value={gender}
                    onChange={handleChangeAge}
                    label="Gender"
                >
                    <MenuItem value='K'>K</MenuItem>
                    <MenuItem value='M'>M</MenuItem>
                </Select>
            </FormControl>
        <TextField label="Below Age" type="number" onChange={handleBelowAge}/>
        <TextField label="Upper Age" type="number" onChange={handleUpperAge}/>
        <TextField label="interest" onChange={handleInterest}/>
        <Button onClick={handleSubmit}>Submit</Button>
        </form>
</Box>
)
}

export default UserPreferences;