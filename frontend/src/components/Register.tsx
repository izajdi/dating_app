import React, {useState} from 'react';

import '../styles/App.css';


const regexpEmail = new RegExp("[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+");
// const regexpPass = new RegExp("/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8})$/");
var regexpPass = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;

const Register = () => {

    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth()+1)>9 ? today.getMonth()+1 : `0${today.getMonth()+1}` ;
    const day = today.getDate()>10 ? today.getDate() : "0"+today.getDate();
    const _date = [year,month,day].join('-');


    
    const [name,setName] = useState("");
    const [mail,setMail] = useState("");
    const [birthDate,setBirthDate] = useState("");
    const [country,setCountry] = useState("");
    const [city,setCity] = useState("");
    const [gender,setGender] = useState("");
    const [password,setPassword] = useState("");
    const [password2,setPassword2] = useState("");



    const handleOnNameChange = (e:React.FormEvent<HTMLInputElement>) => setName(e.currentTarget.value);
    const handleOnEmailChange = (e:React.FormEvent<HTMLInputElement>) => setMail(e.currentTarget.value);
    const handleOnBirthdateChange = (e:React.FormEvent<HTMLInputElement>) => setBirthDate(e.currentTarget.value);
    const handleOnCountryChange = (e:React.FormEvent<HTMLInputElement>) => setCountry(e.currentTarget.value);
    const handleOnCityChange = (e:React.FormEvent<HTMLInputElement>) => setCity(e.currentTarget.value);

    const handleOnGenderChange = (e: React.FormEvent<HTMLSelectElement>) => setGender(e.currentTarget.value);

    const handleOnPass1Change = (e:React.FormEvent<HTMLInputElement>) => setPassword(e.currentTarget.value);
    const handleOnPass2Change = (e:React.FormEvent<HTMLInputElement>) => setPassword2(e.currentTarget.value);



    const handleAddUser = (e)=>{
        e.preventDefault();

        if(name.length===0)alert('podaj imie');

        if(city.length===0)alert('podaj miasto');

        if(!regexpEmail.test(mail))alert('bledny mail');

        if(!regexpPass.test(password) || password.length>6)alert('slabe hasło');

        if(password !== password2)alert('inne hasła');
        
        ;
        


    }


  return (
    <div className="register">
        <form>
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
                <input id="registerBirthDateId"  value={birthDate} max={_date} onChange={handleOnBirthdateChange} type="date"/>
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
                <select id="registerGendersId"  value={gender} onChange={handleOnGenderChange} >
                    <option value="K">K</option>
                    <option value="M" >M</option>
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
            

            <button onClick={handleAddUser} >Zarejestruj</button>
        </form>  
    </div>
  );
}

export default Register;
