import HttpClient from './utils/HttpClient';

class VehicleService {
  constructor() {
    this.HttpClient = new HttpClient('http://localhost:8080');
  }

  listVehicles() {
    return this.HttpClient.get('/veiculos');
  }

  deleteVehicle(id) {
    return this.HttpClient.delete(`/veiculos/${id}`);
  }

  createVehicle(vehicleData) {
    return this.HttpClient.post('/veiculos', { body: vehicleData });
  }
}

export default new VehicleService();
