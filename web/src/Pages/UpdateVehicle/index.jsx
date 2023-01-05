import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router';
import Button from '../../components/Button';
import Input from '../../components/Input';
import Select from '../../components/Select';
import vehicleBrands from '../../services/utils/vehicleBrands';
import VehicleService from '../../services/VehicleService';
import { Form, Label } from './styles';

export default function UpdateVehicle() {
  const [model, setModel] = useState('');
  const [brand, setBrand] = useState('');
  const [year, setYear] = useState('');
  const [color, setColor] = useState('');
  const [isSold, setIsSold] = useState(false);

  const { id } = useParams();

  const navigate = useNavigate();

  function setFieldsValues(data) {
    setModel(data.model ?? '');
    setBrand(data.brand ?? '');
    setYear(data.year.toString() ?? '');
    setColor(data.color ?? '');
    setIsSold(data.isSold ?? false);
  }
  useEffect(() => {
    async function loadVehicle() {
      const vehicleData = await VehicleService.getVehicleById(id);

      setFieldsValues(vehicleData);
    }

    loadVehicle();
  }, [id]);

  const vehicle = {
    model, brand, year: Number(year), color, isSold,
  };

  function handleModelChange({ target }) {
    setModel(target.value);
  }

  function handleBrandChange({ target }) {
    setBrand(target.value);
  }

  function handleIsSoldChange({ target }) {
    setIsSold(target.value);
  }

  function handleColorChange({ target }) {
    setColor(target.value);
  }

  function handleYearChange({ target }) {
    setYear(target.value);
  }

  async function handleSubmit(event) {
    event.preventDefault();

    await VehicleService.updateVehicle(id, vehicle);
    navigate('/');
  }

  return (
    <Form onSubmit={(event) => handleSubmit(event)}>
      <Label>
        Modelo:
        <Input type="text" value={model} onChange={(event) => handleModelChange(event)} />
      </Label>
      <Label>
        Marca:
        <Select type="text" value={brand} onChange={(event) => handleBrandChange(event)}>
          <option>SELECIONE</option>
          {vehicleBrands.map((brandType) => (
            <option key={brandType} value={brandType}>
              {brandType}
            </option>
          ))}
        </Select>
      </Label>
      <Label>
        Ano:
        <Input type="text" value={year} onChange={(event) => handleYearChange(event)} />
      </Label>
      <Label>
        Cor:
        <Input type="text" value={color} onChange={(event) => handleColorChange(event)} />
      </Label>
      <Label>
        Vendido:
        <Select type="text" value={isSold} onChange={(event) => handleIsSoldChange(event)}>
          <option value={false}>
            NÃO
          </option>
          <option value>
            SIM
          </option>
        </Select>
      </Label>
      <Button type="submit">Salvar</Button>
    </Form>
  );
}
