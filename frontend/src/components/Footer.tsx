import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCopyright } from '@fortawesome/free-solid-svg-icons'


import '../styles/Footer.scss';
const Footer = () => {
  return (
    <footer><FontAwesomeIcon icon={faCopyright} /> Michał Kowalczyk {'&'} Bartłomiej Zajda</footer>    
  );
}

export default Footer;
