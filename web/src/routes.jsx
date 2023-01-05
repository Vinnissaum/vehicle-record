import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Home from './Pages/Home';

export default function RoutesProvider() {
  return (
    <Routes>
      <Route path="/" exact element={<Home />} />
    </Routes>
  );
}
