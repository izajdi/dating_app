import React from 'react';
import {Link} from "react-router-dom";

import '../styles/Main.scss';

const Main = () => {
  return (
    <div className="main">
        <button className="btnLogin"> <Link to="/login" >Zaloguj sie</Link></button>
        <button className="btnRegister"> <Link to="/register" >Rejestracja </Link></button>
    </div>
  );
}

export default Main;
