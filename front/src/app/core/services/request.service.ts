import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/shared/models/user/User';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';

const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
    providedIn: 'root'
})
export class RequestService {

    user: User;

    constructor(private http: HttpClient) { }

    canUserPostReview(vehicleId: number, userId : number) {
        return this.http.get<boolean>('server/request/request/canUserPostReview/' + vehicleId + '+' + userId);
    }

    getOwnerRequestHistory() {
        
         this.user = JSON.parse(localStorage.getItem('userObject'));
         let ownerId = this.user.id;
         return this.http.get<Array<BundleDTO>>('server/request/request/ownerRequestHistory?ownerId=' + ownerId);
    }

    getBuyerRequestHistory() {
        
        this.user = JSON.parse(localStorage.getItem('userObject'));
        let userId = this.user.id;
        return this.http.get<Array<BundleDTO>>('server/request/request/buyerRequestHistory?userId=' + userId);
   }

   getOwnerSingleRequests() {
       this.user = JSON.parse(localStorage.getItem('userObject'));
       let ownerId = this.user.id;
       return this.http.get<Array<RequestDTO>>('server/request/request/ownerSingleRequests?ownerId=' + ownerId);
   }

   getBuyerSingleRequests() {
    this.user = JSON.parse(localStorage.getItem('userObject'));
    let userId = this.user.id;
    return this.http.get<Array<RequestDTO>>('server/request/request/buyerSingleRequests?ownerId=' + userId);
}

   changeStatusOfRequest(bundleId: number, changeType: number) {

    return this.http.get<boolean>('server/request/request/changeStatus?bundleId=' + bundleId + '&changeType=' + changeType);
   }


}