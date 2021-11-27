import React, {useState} from 'react';
import '../styles/Register.scss';


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
    // userPreferences
    

    const handleOnNameChange = (e: React.FormEvent<HTMLInputElement>) => setName(e.currentTarget.value);
    const handleOnEmailChange = (e: React.FormEvent<HTMLInputElement>) => setMail(e.currentTarget.value);
    const handleOnBirthdateChange = (e: React.FormEvent<HTMLInputElement>) => setBirthDate(e.currentTarget.value);
    const handleOnCountryChange = (e: React.FormEvent<HTMLInputElement>) => setCountry(e.currentTarget.value);
    const handleOnCityChange = (e: React.FormEvent<HTMLInputElement>) => setCity(e.currentTarget.value);

    const handleOnGenderChange = (e: React.FormEvent<HTMLSelectElement>) => setGender(e.currentTarget.value);

    const handleOnPass1Change = (e: React.FormEvent<HTMLInputElement>) => setPassword(e.currentTarget.value);
    const handleOnPass2Change = (e: React.FormEvent<HTMLInputElement>) => setPassword2(e.currentTarget.value);


    const handleAddUser = (e) => {
        e.preventDefault();

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
    }


    const buildUserObject = (): any => {


        const user = {
            name: name,
            dateOfBirthday: birthDate,
            email: mail,
            country: country,
            city: city,
            gender: gender,
            description: `Mam na imie ${name}`,
            password: passwordHash(password)
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
            <form>
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

                    <label htmlFor="registerPassID">
                        Hasło:
                        <input id="registerPassID" value={password} onChange={handleOnPass1Change} type="password"/>
                    </label>
                    <label htmlFor="registerPass2ID">
                        Powtórz hasło:
                        <input id="registerPass2ID" value={password2} onChange={handleOnPass2Change} type="password"/>
                    </label>


                    <button onClick={handleAddUser}>Zarejestruj</button>
                </div>
            </form>
        </div>
    );
}

export default Register;
