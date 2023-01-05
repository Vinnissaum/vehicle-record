import React from 'react';
import logo from '../../assets/images/logo.png';
import Container from './styles';

export default function Header() {
  return (
    <Container>
      <img src={logo} alt="Tinnova Software Solutions" style={{ width: 214 }} />
    </Container>
  );
}
