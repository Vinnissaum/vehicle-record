import React from 'react';
import { Container, Header } from './styles';

const vehicles = 'teste';

export default function Home() {
  return (
    <Container>
      <Header>
        <strong>
          Veículos:
          {' '}
          {vehicles}
        </strong>
      </Header>
    </Container>
  );
}
