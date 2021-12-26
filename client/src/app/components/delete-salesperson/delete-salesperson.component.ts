import { Component, OnInit } from '@angular/core';
// import salesperson service
import { SalespeopleService } from 'src/app/services/salespeople.service';
// import data service 
import { DataService } from '../../services/data.service';
// import router
import { Router } from '@angular/router';

@Component({
  selector: 'app-delete-salesperson',
  templateUrl: './delete-salesperson.component.html'
})
export class DeleteSalespersonComponent implements OnInit {

  // variables
  salesperson;

  error;

  // get instance of this created
  constructor(private ds: DataService, private sp: SalespeopleService,  private router: Router) { }

  ngOnInit(): void {
    var spid;
    // spid is getting the spid from the data service 
    spid = this.ds.getSpid();
    // getting the salesperson service - this gets salesperson
    this.sp.getSalesperson(spid).subscribe(
      // if everything is successful, variable is get called salesperson
      (data) => { this.salesperson = data},
      // print out the error
      (error) => { console.log(error)}
    )
  }

  // delete the salesperson 
  delete(spid: string){
    this.sp.deleteSalesperson(spid).subscribe(
      // when successful
      (data) => { this.router.navigate(['salespeople'])},
      // print out the error
      (error) => { this.error = error.error.message}
    )
  }

}
