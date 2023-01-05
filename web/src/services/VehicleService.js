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
}

export default new VehicleService();
