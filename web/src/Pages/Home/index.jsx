import React, { useCallback, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import VehicleService from '../../services/VehicleService';
import { Container, Header, Card } from './styles';
import Button from '../../components/Button';

export default function Home() {
  const [vehicles, setVehicles] = useState([]);

  const loadVehicles = useCallback(async () => {
    const vehicleList = await VehicleService.listVehicles();
    setVehicles(vehicleList);
  });

  useEffect(() => {
    loadVehicles();
  }, [loadVehicles]);

  async function handleDeleteVehicle(vehicleToDeleteId) {
    await VehicleService.deleteVehicle(vehicleToDeleteId);
    setVehicles((prevState) => prevState.filter(({ id }) => (
      id !== vehicleToDeleteId
    )));
  }

  return (
    <Container>
      <Header>
        <strong>
          {vehicles.length}
          {' '}
          {vehicles.length === 1 ? 'Veículo' : 'Veículos'}
        </strong>
        <Link to="/cadastrar">Adicionar Veículo</Link>
      </Header>
      {vehicles.map((vehicle) => (
        <Card key={vehicle.id}>
          <div className="info">
            <div className="model">
              <strong>{ vehicle.model }</strong>
            </div>
            <span>{vehicle.brand}</span>
            <span>
              Ano:
              {' '}
              {vehicle.year}
            </span>
            <span>
              Vendido:
              {' '}
              {vehicle.isSold ? 'Sim' : 'Não'}
            </span>
          </div>

          <div className="actions">
            <Link to={`/editar/${vehicle.id}`} style={{ textDecoration: 'none' }}>
              <Button
                type="button"
              >
                Alterar
              </Button>
            </Link>

            <Button
              type="button"
              onClick={() => handleDeleteVehicle(vehicle.id)}
            >
              Excluir
            </Button>
          </div>
        </Card>
      ))}
    </Container>
  );
}
