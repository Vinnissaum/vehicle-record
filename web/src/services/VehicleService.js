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

  updateVehicle(id, vehicleData) {
    return this.HttpClient.put(`/veiculos/${id}`, { body: vehicleData });
  }

  createVehicle(vehicleData) {
    return this.HttpClient.post('/veiculos', { body: vehicleData });
  }

  async getVehicleById(id) {
    return this.HttpClient.get(`/veiculos/${id}`);
  }
}

export default new VehicleService();
