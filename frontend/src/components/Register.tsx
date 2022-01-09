import React, {useState} from 'react';
import '../styles/Register.scss';
import {Redirect} from 'react-router';
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Grid from "@mui/material/Grid";


// var passwordHash = require('password-hash');
var passwordHash = require('md5');

const regexpEmail = new RegExp("[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+");
// const regexpPass = new RegExp("/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8})$/");
var regexpPass = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;

const Register = () => {

    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1) > 9 ? today.getMonth() + 1 : `0${today.getMonth() + 1}`;
    const day = today.getDate() >= 10 ? today.getDate() : "0" + today.getDate();
    const _date = [year, month, day].join('-');


    const [name, setName] = useState("");
    const [mail, setMail] = useState("");
    const [birthDate, setBirthDate] = useState(_date);
    const [country, setCountry] = useState("");
    const [city, setCity] = useState("");
    const [gender, setGender] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [intrested, setIntrested] = React.useState("");
    const [mbtiType, setMbtiType] = useState("");
    const [description, setDescription] = useState("");
    // userPreferences

    const removeIntret = (intrestes, intrest) => {
        const newInt = intrestes.filter((i) => i !== intrest);
        setIntrested(newInt.join(","));
    };


    const handleOnNameChange = (e: React.FormEvent<HTMLInputElement>) => setName(e.currentTarget.value);
    const handleOnEmailChange = (e: React.FormEvent<HTMLInputElement>) => setMail(e.currentTarget.value);
    const handleOnBirthdateChange = (e: React.FormEvent<HTMLInputElement>) => setBirthDate(e.currentTarget.value);
    const handleOnCountryChange = (e: React.FormEvent<HTMLInputElement>) => setCountry(e.currentTarget.value);
    const handleOnCityChange = (e: React.FormEvent<HTMLInputElement>) => setCity(e.currentTarget.value);

    const handleOnGenderChange = (e: React.FormEvent<HTMLSelectElement>) => setGender(e.currentTarget.value);

    const handleOnPass1Change = (e: React.FormEvent<HTMLInputElement>) => setPassword(e.currentTarget.value);
    const handleOnPass2Change = (e: React.FormEvent<HTMLInputElement>) => setPassword2(e.currentTarget.value);
    const handleOnIntrestChange = (e) => {
        if (e.target.checked) {
            return setIntrested((prev) =>
                prev !== "" ? prev + "," + e.target.value : e.target.value
            );
        }
        return removeIntret(intrested.split(","), e.target.value);
    }
    const handleOnMbtiTypeChange = (e: React.FormEvent<HTMLSelectElement>) => setMbtiType(e.currentTarget.value);
    const handleOnInterestsChange = (e: React.FormEvent<HTMLSelectElement>) => setMbtiType(e.currentTarget.value);
    const handleOnDescriptionChange = (e: React.FormEvent<HTMLInputElement>) => setDescription(e.currentTarget.value);


    const handleAddUser = () => {
        if (name.length === 0) {
            alert('podaj imie');
            return;

        }

        if (!regexpEmail.test(mail)) {

            alert('bledny mail');
            return;


        }

        if (Number(birthDate.slice(0, 4)) > year - 14) {
            alert("Nie masz 14 lat");
            return;
        }

        if (country.length === 0) {
            alert('podaj kraj');
            return;
        }


        if (city.length === 0) {
            alert('podaj miasto');
            return;
        }

        if (gender === "") {
            alert("podaj plec");
            return;
        }
        if (password !== password2) {
            alert('inne hasła');
            return;
        }
        sendUserObject();
        return <Redirect to="/"/>
    }


    const buildUserObject = (): any => {


        const user = {
            name: name,
            dateOfBirthday: birthDate,
            email: mail,
            country: country,
            city: city,
            gender: gender,
            description: description,
            interests: intrested,
            mbtiType: mbtiType,
            password: password
        }

        return user;
    }


    const clearForm = () => {
        setName("");
        setMail("");
        setBirthDate(_date);
        setCountry("")
        setCity("");

        setGender("");

        setPassword("");
        setPassword2("");

    }

    const sendUserObject = () => {
        //     console.log(buildUserObject())
        //    const userJson =JSON.stringify(buildUserObject()) ;

        //     console.log(userJson);

        const requestOptions = {
            method: 'POST',
            headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify(buildUserObject())
        };

        fetch('http://localhost:8080/api/user/add', requestOptions)
            .then(response => {
                if (response.status === 200) {
                    alert("Użytkownik zarejestrowany!");
                    clearForm();
                    return response.json();
                } else if (response.status === 422) {
                    alert("Użytkownik o podanym adresie email juz istnieje!");
                } else {
                    alert("Rejestracja nieudana. Spróbuj ponownie!");
                }
            })
            .then(data => console.log(data))
            .catch(err => console.log(err));

    }

    console.log(_date);
    return (


        <div className="register">
            <form action="http://localhost:3000/">
                <div>
                    <label htmlFor="registerNameId">
                        Imie:
                        <input id="registerNameId" value={name} onChange={handleOnNameChange} type="text" required/>
                    </label>
                    <label htmlFor="registerMailId">
                        E-mail:
                        <input id="registerMailId" value={mail} onChange={handleOnEmailChange} type="text"/>
                    </label>
                    <label htmlFor="registerBirthDateId">
                        Data urodzenia:
                        <input id="registerBirthDateId" value={birthDate} max={_date} onChange={handleOnBirthdateChange}
                               type="date"/>
                    </label>
                    <label htmlFor="registerCountryId">
                        Kraj:
                        <input id="registerCountryId" value={country} onChange={handleOnCountryChange} type="text"/>
                    </label>
                    <label htmlFor="registerCityId">
                        Miasto:
                        <input id="registerCityId" value={city} onChange={handleOnCityChange} type="text"/>
                    </label>
                    <label htmlFor="registerGendersId">
                        Płec:
                        <select id="registerGendersId" value={gender} onChange={handleOnGenderChange}>
                            <option value=""></option>
                            <option value="K">K</option>
                            <option value="M">M</option>
                        </select>
                    </label>

                    <label htmlFor="registerMbtiType">
                        Typ osobowosci MBTI:
                        <select id="registerGendersId" value={mbtiType} onChange={handleOnMbtiTypeChange}>
                            <option value=""></option>
                            <option value="INFP">INFP</option>
                            <option value="ENFP">ENFP</option>
                            <option value="INFJ">INFJ</option>
                            <option value="ENFJ">ENFJ</option>
                            <option value="INTJ">INTJ</option>
                            <option value="ENTJ">ENTJ</option>
                            <option value="INTP">INTP</option>
                            <option value="ENTP">ENTP</option>
                            <option value="ISFP">ISFP</option>
                            <option value="ESFP">ESFP</option>
                            <option value="ISTP">ISTP</option>
                            <option value="ESTP">ESTP</option>
                            <option value="ISFJ">ISFJ</option>
                            <option value="ESFJ">ESFJ</option>
                            <option value="ISTJ">ISTJ</option>
                            <option value="ESTJ">ESTJ</option>
                        </select>
                    </label>

                    <label>
                        Wybierz zainteresowania:
                    </label>
                        <FormGroup row sx={{
                            marginLeft: "-650px",
                            display: "flex",
                            alignContent: "stretch",
                            flexWrap: "nowrap",
                            alignItems: "center",
                            flexDirection: "row",
                            justifyContent: "flex-end",
                        }}>
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        value="zwierzeta"
                                        label="zwierzeta"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        label="sport"
                                        value="sport"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        label="ksiazki"
                                        value="ksiazki"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        label="filmy"
                                        value="filmy"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        value="religia"
                                        label="religia"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        label="natura"
                                        value="natura"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        label="imprezy"
                                        value="imprezy"
                                    />
                                    <FormControlLabel
                                        control={<Checkbox onClick={handleOnIntrestChange}/>}
                                        label="moda"
                                        value="moda"
                                    />
                                </FormGroup>

                    <label htmlFor="registerPassID">
                        Hasło:
                        <input id="registerPassID" value={password} onChange={handleOnPass1Change} type="password"/>
                    </label>
                    <label htmlFor="registerPass2ID">
                        Powtórz hasło:
                        <input id="registerPass2ID" value={password2} onChange={handleOnPass2Change} type="password"/>
                    </label>
                    <label htmlFor="registerDescriptionID">
                        Opis:
                        <input id="registerDescriptionID" value={description} onChange={handleOnDescriptionChange}
                               type="text"/>
                    </label>
                    <button onClick={handleAddUser}>Zarejestruj</button>
                </div>
            </form>
        </div>
    );
}

export default Register;
