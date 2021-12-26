import { Component, OnInit } from '@angular/core';
// import salesperson service
import { SalespeopleService } from 'src/app/services/salespeople.service';
// import data service 
import { DataService } from '../../services/data.service';
// import router
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-salesperson',
  templateUrl: './update-salesperson.component.html'
})
export class UpdateSalespersonComponent implements OnInit {

  constructor(private ds: DataService, private sp: SalespeopleService,  private router: Router) { }

   // variables
   salesperson = {
     spid: "",
     name: ""
   };

  error;

  ngOnInit(): void {
    
  }

  // update the salesperson - (not fully working)
  update(spid: string, name: string){

    this.sp.updateSalesperson(spid, name).subscribe(
      // when successful
      (data) => { this.router.navigate(['salespeople'])},
      // print out the error
      (error) => { this.error = error.error.message}
    )
  }

}
