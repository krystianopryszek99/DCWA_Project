import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import delete salesperson component
import { DeleteSalespersonComponent } from './components/delete-salesperson/delete-salesperson.component';
// import new salesperson component
import { NewSalespersonComponent } from './components/new-salesperson/new-salesperson.component';
// import salesperson component
import { SalespeopleComponent } from './components/salespeople/salespeople.component';
// import update salesperson component
import { UpdateSalespersonComponent } from './components/update-salesperson/update-salesperson.component';

// all the paths to salesperson component, new salesperson, delete and update
const routes: Routes = [
  {path: 'salespeople', component: SalespeopleComponent},
  {path: 'salespeople/newSalesperson', component: NewSalespersonComponent},
  {path: 'deleteSalesperson', component: DeleteSalespersonComponent},
  {path: 'updateSalesperson', component: UpdateSalespersonComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// all exported then 
export const routingComponents = [ SalespeopleComponent, NewSalespersonComponent, DeleteSalespersonComponent, UpdateSalespersonComponent ]
