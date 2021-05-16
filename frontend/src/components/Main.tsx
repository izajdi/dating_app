import React from 'react';
import {Link} from "react-router-dom";



const Main = () => {
  return (
    <div className="main">
        <button> <Link to="/login" >Zaloguj sie</Link></button>
        <button> <Link to="/register" >Rejestracja </Link></button>
    </div>
  );
}

export default Main;
