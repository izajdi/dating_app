import React, {useState} from 'react';

import '../styles/App.css';

const Register = () => {
    
    const [name,setName] = useState("");
    const [mail,setMail] = useState("");
    const [birthDate,setBirthDate] = useState("");
    const [country,setCountry] = useState("");
    const [city,setCity] = useState("");
    const [gender,setGender] = useState("");
    const [password,setPassword] = useState("");
    const [password2,setPassword2] = useState("");



    const handleAddUser = (e)=>{
        e.preventDefault();
    }


  return (
    <div className="register">
        <form>
            <label htmlFor="registerNameId">
                Imie:
                <input id="registerNameId" value={name} type="text"/>
            </label>    
            <label htmlFor="registerMailId">
                E-mail:
                <input id="registerMailId" value={mail} type="text"/>
            </label> 
            <label htmlFor="registerBirthDateId">
                Data urodzenia: 
                <input id="registerBirthDateId"  value={birthDate} type="text"/>
            </label> 
            <label htmlFor="registerCountryId">
                Kraj:
                <input id="registerCountryId" value={country} type="text"/>
            </label> 
            <label htmlFor="registerCityId">
                Miasto:
                <input id="registerCityId" value={city} type="text"/>
            </label> 
            <label htmlFor="registerGendersId">
                Płec:
                <select id="registerGendersId">
                    <option value="K">K</option>
                    <option value="M" >M</option>
                </select>
            </label> 
           
            <label htmlFor="registerPassID">
                Hasło:
                <input id="registerPassID" value={password} type="password"/>
            </label> 
            <label htmlFor="registerPass2ID">
                Powtórz hasło:
                <input id="registerPass2ID" value={password2} type="password"/>
            </label> 
            

            <button onClick={handleAddUser} >Zarejestruj</button>
        </form>  
    </div>
  );
}

export default Register;
