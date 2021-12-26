import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  // DataService - for storing data 

  // stores the spid
  spid: string;

  constructor() { }

  // set the spid 
  setSpid(spid: string){
    this.spid = spid;
  }

  // gets the spid 
  getSpid(){
    // returns spid
    return this.spid;
  }


}
