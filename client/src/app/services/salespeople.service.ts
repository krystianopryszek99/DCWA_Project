import { Injectable } from '@angular/core';
// importing HttpClient
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SalespeopleService {

  // instance of the Http Client
  constructor(private http: HttpClient) { }

  //method get salespeople 
  getSalespeople(){
    return this.http.get('/server/api/salespeople')
  }

  //gets salesperson by spid 
  getSalesperson(spid){
    return this.http.get('/server/api/salespeople/' + spid)
  }

  //add new salesperson
  newSalesperson(salesperson){
    return this.http.post('server/api/salespeople', salesperson);
  }

  //delete salesperson
  deleteSalesperson(spid){
    return this.http.delete('/server/api/salespeople/' + spid)
  }

  // update salesperson
  updateSalesperson(spid, salesperson){
    return this.http.put('/server/api/salespeople/' + spid, salesperson)
  }
}
