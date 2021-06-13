import React,{useState} from 'react';
import '../styles/FilterForm.scss'

type FilterFormProps = {
  handleFilter: any,
  refresh:any
  
}

const FilterForm = ({handleFilter,refresh}:FilterFormProps) => {

    const [ageMin,setAgeMin] = useState(0);
    const [ageMax,setAgeMax] = useState(99);
    const [city,setCity] = useState("");
    const [gender,setGender] = useState("");

  const handleReset =(e)=>{
    e.preventDefault();
    setAgeMin(0);
    setAgeMax(99);
    setCity("");
    setGender("");
    refresh(prev => !prev);
  }
  const handleSearch =(e)=>{
    e.preventDefault();
    handleFilter(ageMin,ageMax,city,gender);
  }


  const handleAgeMinChange =(e:React.FormEvent<HTMLInputElement>) => setAgeMin(Number(e.currentTarget.value));
  const handleAgeMaxChange =(e:React.FormEvent<HTMLInputElement>) => setAgeMax(Number(e.currentTarget.value));
  const handleCityChange =(e:React.FormEvent<HTMLInputElement>) => setCity(e.currentTarget.value);
  const handleGenderChange =(e:React.FormEvent<HTMLSelectElement>) => setGender(e.currentTarget.value);

  

  return (
    <div className="filterForm"> 
      <form >

          <label htmlFor="ageMin">
                Wiek min <br/>
                <input id="ageMin" type="number" min="0" max="99" value={ageMin} onChange={handleAgeMinChange}/>
            </label> 
            <label htmlFor="ageMax">
                Wiek max <br/>
                <input id="ageMax" type="number" min="0" max="99" value={ageMax} onChange={handleAgeMaxChange}/>
            </label> 
            <label htmlFor="registerCity">
                Miasto: <br/>
                <input id="registerCity" value={city} onChange={handleCityChange}/>
            </label> 
            <label htmlFor="registerGenders">
                Płec:<br/>
                <select id="registerGenders"  value={gender} onChange={handleGenderChange} >
                    <option value=""></option>
                    <option value="K">K</option>
                    <option value="M" >M</option>
                </select>
            </label> 
        <button onClick={handleReset}>reset</button>
        <button onClick={handleSearch}>szukaj</button>
      </form>
    </div>
  );
}

export default FilterForm;
