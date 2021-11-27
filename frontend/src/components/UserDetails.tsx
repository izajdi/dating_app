import React, {useState, useEffect} from 'react';
import axios from 'axios';
import '../styles/UserDetails.scss'
import FormData from 'form-data';
import blankProfile from '../images/blankProfile.jpg';
import UserDetailsForm from './UserDetailsForm';
import UserPreferences  from "./UserPreferences";

var passwordHash = require('md5');
// var FormData = require('form-data');

type UserDetailsProps = {
    user: any,
    refresh: any
}


const UserDetails = ({user, refresh}: UserDetailsProps) => {

    const [editMode, setEditMode] = useState(false);
    const [photo, setPhoto] = useState(undefined as any);
    const [isPhotoFormActive, setIsPhotoFormActive] = useState(false);
    const [newPhoto, setNewPhoto] = useState('');
    const [loading, setLoading] = useState(false);
    const [preferences, setPreferences] = useState(false);

    const togglePreferences = () => setPreferences((prev) => !prev)


// toggle edit mode
    const handleOnClick = () => {
        setEditMode(prev => !prev);
    }

    // photo change
    const handleChangePhoto = (e) => {
        setPhoto(e.currentTarget.files[0]);
    }

    // ???
    const handleChangePhotoForm = () => {
        setIsPhotoFormActive(true);
    }

    // uploadImage
    const changePhoto = (e) => {
        e.preventDefault();
        if (!photo) return;
        setIsPhotoFormActive(false);

        let form_data = new FormData();
        form_data.append('file', photo);
        // form_data.append('userId', user.id);

        let url = `http://localhost:8080/image/upload/${user.id}`;

        setLoading(true);
        axios.put(url, form_data, {
            headers: {
                'content-type': 'image/jpeg'
            }
        })
            .then(res => {
                setLoading(false);
                console.log(res.data);
            })
            .catch(err => console.log(err))


        setIsPhotoFormActive(false);
        setPhoto(undefined as any);
    }



    useEffect( () => {
        const onPhotoGet = async () => {
            let url = `http://localhost:8080/image/get/${user?.id}`;

            try {
                const res = await axios.get(url, {responseType: "arraybuffer"});
                console.log(res.data);
                const base64ImgString = Buffer.from(res.data, 'binary').toString('base64');
                const srcValue = 'data:image/png;base64,' + base64ImgString;
                setNewPhoto(srcValue);
            }catch(e){
                console.log(e)
            }

        }

        onPhotoGet();
    }, [newPhoto, user, loading])

    const handleClearPhotoForm = (e) => {
        // e.preventDefault();
        setIsPhotoFormActive(false);
        setPhoto(undefined as any);
    }

    const photoForm = () => (
        !loading ? (
            <>
            <form className="photoForm" onSubmit={changePhoto}>
            <input className="photoInput" type="file" name="picture" onChange={handleChangePhoto}/>
            <button onClick={handleClearPhotoForm}>Anuluj</button>
            <button>Zmien</button>

        </form></>) : <p>'Zmiana...'</p>
    )

    const renderPhoto = () => {
        if(newPhoto != ''){
            return newPhoto
        }

        return blankProfile;
    }


    const userDetails = <>
        <img src={renderPhoto()} alt="" onClick={handleChangePhotoForm}/>
        <br/>
        {photoForm()}

        <p><span>Imie: </span>{user && user.name}</p>
        <p><span>Email: </span>{user && user.email}</p>
        <p><span>Data urodzenia: </span>{user && user.dateOfBirthday}</p>
        <p><span>Panstwo: </span>{user && user.country}</p>
        <p><span>Miasto: </span>{user && user.city}</p>
        <p><span>Pleć: </span>{user && user.gender}</p>
        <p><span>Opis: </span>{user && user.description}</p>
    </>


    const userDetailsForm = user && <UserDetailsForm
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
        changeUser={() => setEditMode(false)}
    />

    const renderViewMode = () => {
        if(editMode){
            return (
                <>
                <button className="disable" onClick={handleOnClick}>{editMode ? "Anuluj" : "Edytuj"}</button>
                    {userDetailsForm}
                </>
            )
        }

        return (<>
            <button className="disable" onClick={handleOnClick}>{editMode ? "Anuluj" : "Edytuj"}</button>
            {userDetails}
        </>)
    }
    return (
        <div className="userDetails">
            {preferences ? <UserPreferences userId={user.id}/>:renderViewMode() }
            <p><button onClick={togglePreferences}>{!preferences ? 'Set preferences' : 'Anuluj'}</button></p>
        </div>
    );
}

export default UserDetails;
