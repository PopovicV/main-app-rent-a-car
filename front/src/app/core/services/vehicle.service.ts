import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { Vehicle } from 'src/app/shared/models/vehicle/Vehicle';
import { SearchParams } from 'src/app/shared/models/SearchParams';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<VehicleMainViewDTO[]>('server/vehicle/search',  httpOptions);
  }

  getVehicle(vehicleId : number) {
    return this.http.get<Vehicle>('server/vehicle/vehicle/'+vehicleId,  httpOptions);
  }

  uploadPicture(uploadImageData : any) {
    return this.http.post<any>('server/vehicle/vehicle/uploadPicture', uploadImageData,  httpOptions);
  }

  create(vehicle : Vehicle) {
    return this.http.post<any>('server/vehicle/vehicle/', vehicle,  httpOptions);
  }

  search(searchParams : SearchParams) {
    let params = new HttpParams();

    params = params.append('vehicleMake', searchParams.vehicleMake.toString())
    params = params.append('vehicleModel', searchParams.vehicleModel.toString())
    params = params.append('vehicleStyle', searchParams.vehicleStyle.toString())
    params = params.append('vehicleFuel', searchParams.vehicleFuel.toString())
    params = params.append('vehicleTransmission', searchParams.vehicleTransmission.toString())

    params = params.append('priceLowerLimit', searchParams.priceLowerLimit.toString())
    params = params.append('priceUpperLimit', searchParams.priceUpperLimit.toString())

    params = params.append('maxMileage', searchParams.maxMileage.toString())
    params = params.append('mileageLimit', searchParams.mileageLimit.toString())

    params = params.append('collisionProtection', searchParams.collisionProtection.toString())
    params = params.append('childrenSeats', searchParams.childrenSeats.toString())

    params = params.append('state', searchParams.state.toString())
    params = params.append('city', searchParams.city.toString())

    if (searchParams.startDate == null || searchParams.endDate == null){
      params = params.append('startDate', "")
    params = params.append('endDate', "")
    }
    else{
      params = params.append('startDate', searchParams.startDate.toISOString())
      params = params.append('endDate', searchParams.endDate.toISOString())
    }

    const options = {
      headers : new HttpHeaders({'Content-Type' : 'application/json'}),
      params : params
    }

    return this.http.get<VehicleMainViewDTO[]>('server/vehicle/search/search',  options);
  }

}