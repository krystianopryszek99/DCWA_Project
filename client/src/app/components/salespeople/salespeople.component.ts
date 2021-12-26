import { Component, OnInit } from '@angular/core';
// importSalespeople Service
import { SalespeopleService } from '../../services/salespeople.service';
// router
import { Router } from '@angular/router';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-salespeople',
  templateUrl: './salespeople.component.html'
})
export class SalespeopleComponent implements OnInit {
  
  //variables

  // salespeople to get the data 
  salespeople;
  // error - displaying the errors 
  error;

  // putting SalespeopleService inside a constructor
  constructor(private sp: SalespeopleService, private ds: DataService,  private router: Router) { }

  // use sp
  ngOnInit(): void {
    this.sp.getSalespeople().subscribe(
      // when everything is successful
      (data) => {this.salespeople = data},
      // print out the error
      (error) => { this.error = error.message}
    )
  }

  // salesperson details - each spid is retured 
  salespersonDetails(spid: string){
    // prints out the spid in the console
    console.log(spid);
    // calls the data service and sets the spid
    // stores the spid in data service
    this.ds.setSpid(spid);
    // navigates to /deleteSalesperson
    this.router.navigate(['deleteSalesperson'])
  }

  update(){
    this.router.navigate(['updateSalesperson'])
  }

}
