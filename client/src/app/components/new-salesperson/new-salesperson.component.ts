import { Component, OnInit } from '@angular/core';
// import router
import { Router } from '@angular/router';
// import salesperson service
import { SalespeopleService } from 'src/app/services/salespeople.service';

@Component({
  selector: 'app-new-salesperson',
  templateUrl: './new-salesperson.component.html'
})
export class NewSalespersonComponent implements OnInit {

  // variables
  salesperson = {
    spid: "",
    name: ""
  }
 
  error;
  
  // salesperson service to call newSalesperson and router for navigation to salespeople when the data is retrived
  constructor(private sp: SalespeopleService, private router:Router) { }

  ngOnInit(): void {
  }

  // save method
  onSave(spid: string, name: string) { 

    // new salesperson - add the spid and name
    var newSalesperson = {
      spid: spid,
      name: name
    }
    
    // new salesperson - data gets send to the server 
    this.sp.newSalesperson(newSalesperson).subscribe(
      // if everything is successful 
      (data) => {this.router.navigate(['salespeople'])},
      // for any errors - this gets then called and return the specific error message on screen
      (error) => {this.error = error.error.message}
    )

  }
  
}
