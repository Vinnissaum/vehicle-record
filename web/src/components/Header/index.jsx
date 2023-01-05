import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../../assets/images/logo.png';
import Container from './styles';

export default function Header() {
  return (
    <Container>
      <Link to="/">
        <img src={logo} alt="Tinnova Software Solutions" style={{ width: 214 }} />
      </Link>
    </Container>
  );
}
