import React from 'react';
import { ThemeProvider } from 'styled-components';
import { BrowserRouter } from 'react-router-dom';

import defaultTheme from '../../assets/styles/themes/default';
import GlobalStyle from '../../assets/styles/global';
import Routes from '../../routes';
import Container from './styles';
import Header from '../Header';

function App() {
  return (
    <BrowserRouter>
      <ThemeProvider theme={defaultTheme}>
        <GlobalStyle />
        <Container>
          <Header />
          <Routes />
        </Container>
      </ThemeProvider>
    </BrowserRouter>
  );
}

export default App;
