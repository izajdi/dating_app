import React,{useState} from 'react';

var passwordHash = require('md5');


const regexpEmail = new RegExp("[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+");
// const regexpPass = new RegExp("/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8})$/");
var regexpPass = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
type UserDetailsFormProps = {
    userId:string,
    name:string,
    email:string,
    dateOfBirthday:string,
    country:string,
    city:string,
    description:string,
    password:string,
    refresh:any,
    changeUser:any
  }

const UserDetailsForm = ({userId,name,email,dateOfBirthday,country,city,description,password,refresh,changeUser}:UserDetailsFormProps) => {

    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth()+1)>9 ? today.getMonth()+1 : `0${today.getMonth()+1}` ;
    const day = today.getDate()>=10 ? today.getDate() : "0"+today.getDate();
    const _date = [year,month,day].join('-');

  

  
  

  const [newName,setNewName] = useState(name || "");
  const [newEmail,setNewMail] = useState(email || "");
  const [newBirthDate,setNewBirthDate] = useState(dateOfBirthday || "");
  const [newCountry,setNewCountry] = useState(country || "");
  const [newCity,setNewCity] = useState(city || "");
  const [newGender,setNewGender] = useState("");
  const [newDescription,setNewDescription] = useState(description || "");
  const [oldPassword,setOldPassword] = useState("");
  const [newPassword,setPassword] = useState("");
  const [newPassword2,setPassword2] = useState("");


  const handleOnNameChange = (e:React.FormEvent<HTMLInputElement>) => setNewName(e.currentTarget.value);
  const handleOnEmailChange = (e:React.FormEvent<HTMLInputElement>) => setNewMail(e.currentTarget.value);
  const handleOnBirthdateChange = (e:React.FormEvent<HTMLInputElement>) => setNewBirthDate(e.currentTarget.value);
  const handleOnCountryChange = (e:React.FormEvent<HTMLInputElement>) => setNewCountry(e.currentTarget.value);
  const handleOnCityChange = (e:React.FormEvent<HTMLInputElement>) => setNewCity(e.currentTarget.value);

  const handleOnGenderChange = (e: React.FormEvent<HTMLSelectElement>) => setNewGender(e.currentTarget.value);
  const handleOnDescriptionChange = (e: React.FormEvent<HTMLTextAreaElement>) => setNewDescription(e.currentTarget.value);
  const handleOnOldPassChange = (e:React.FormEvent<HTMLInputElement>) => setOldPassword(e.currentTarget.value);
  const handleOnPass1Change = (e:React.FormEvent<HTMLInputElement>) => setPassword(e.currentTarget.value);
  const handleOnPass2Change = (e:React.FormEvent<HTMLInputElement>) => setPassword2(e.currentTarget.value);



  const buildUserObject = ():any=>{


    const user = {
        name:newName,
        dateOfBirthday:newBirthDate, 
        email:newEmail,
        country:newCountry,
        city:newCity,
        description:newDescription,
        password:passwordHash(newPassword)
        
        
    
    }

    return user;


}

  const handleEditUser = (e)=>{
    e.preventDefault();
    
    


    if(newName.length===0){
        alert('podaj imie');
        return;
    
    }

    if(!regexpEmail.test(newEmail)){

        alert('bledny mail');
        return;


    }

    if(Number(newBirthDate.slice(0,4)) > year-14){
        
        alert("Nie masz 14 lat");
        return;

}

    if(newCountry.length===0){
        alert('podaj kraj');
        return;
    }


    if(newCity.length===0){
        alert('podaj miasto');
        return;
    }

if(newGender==="" ){
    alert("podaj plec");
    return;
}
        


if(passwordHash(oldPassword) !== password){
    alert('Stare hasło nieprawidłowe');
    return;
}

    if(!regexpPass.test(newPassword) || newPassword.length<6){
        alert('slabe hasło');
        return;
    }
    if(newPassword !== newPassword2){
        alert('inne hasła');
        return;
    }

    
    editUserObject();
  }

  const editUserObject = () => {
    //     console.log(buildUserObject())
    //    const userJson =JSON.stringify(buildUserObject()) ;

        // console.log(userJson);
        // console.log(userId);

        const requestOptions = {
            method: 'PUT',
            headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
            body: JSON.stringify(buildUserObject()) 
        };

        fetch(`http://localhost:8080/api/user/${userId}`, requestOptions)
        .then(response => {
            if(response.status === 200){
                alert("Udało sie!");
                // clearForm();
                changeUser();
                refresh();
                // tutaj powinien byc reRender strony nadrzednej z nowymi danymi
                return  response.json();
            }
            else if(response.status ===400){
                alert("Error!");
            }else{
                alert("Spróbuj ponownie!");
            }
        })
        .then(data =>console.log(data))
        .catch(err => console.log(err));

    }


  const formUserDetails = <>
          <form>
            <label htmlFor="registerNameId">
                Imie:
                <input id="registerNameId" value={newName} onChange={handleOnNameChange} type="text" required/>
            </label>    
            <label htmlFor="registerMailId">
                E-mail:
                <input id="registerMailId" value={newEmail} onChange={handleOnEmailChange} type="text"/>
            </label> 
            <label htmlFor="registerBirthDateId">
                Data urodzenia: 
                <input id="registerBirthDateId"  value={newBirthDate} max={_date} onChange={handleOnBirthdateChange} type="date"/>
            </label> 
            <label htmlFor="registerCountryId">
                Kraj:
                <input id="registerCountryId" value={newCountry} onChange={handleOnCountryChange} type="text"/>
            </label> 
            <label htmlFor="registerCityId">
                Miasto:
                <input id="registerCityId" value={newCity} onChange={handleOnCityChange} type="text"/>
            </label> 
            <label htmlFor="registerGendersId">
                Płec:
                <select id="registerGendersId"  value={newGender} onChange={handleOnGenderChange} >
                    <option value=""></option>
                    <option value="K">K</option>
                    <option value="M" >M</option>
                </select>
            </label> 
            <label htmlFor="descriptionId">
                Opis:
                <textarea id="descriptionId" value={newDescription} onChange={handleOnDescriptionChange} />
            </label> 
            <label htmlFor="registerOldPassID">
                Stare Hasło:
                <input id="registerOldPassID" value={oldPassword} onChange={handleOnOldPassChange} type="password"/>
            </label> 
            <label htmlFor="registerPassID">
                Nowe Hasło:
                <input id="registerPassID" value={newPassword} onChange={handleOnPass1Change} type="password"/>
            </label> 
            <label htmlFor="registerPass2ID">
                Powtórz hasło:
                <input id="registerPass2ID" value={newPassword2} onChange={handleOnPass2Change} type="password"/>
            </label> 
            

            <button onClick={handleEditUser} >Zatwierdz</button>
        </form> 

                        </>

  return (
    <div className="userDetailForm" >
        {formUserDetails}
        
        
    </div>
  );
}

export default UserDetailsForm;
