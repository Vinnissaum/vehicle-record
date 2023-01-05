import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Home from './Pages/Home';
import NewVehicle from './Pages/NewVehicle';
import UpdateVehicle from './Pages/UpdateVehicle';

export default function RoutesProvider() {
  return (
    <Routes>
      <Route path="/" exact element={<Home />} />
      <Route path="/editar/:id" exact element={<UpdateVehicle />} />
      <Route path="/cadastrar" exact element={<NewVehicle />} />
    </Routes>
  );
}
