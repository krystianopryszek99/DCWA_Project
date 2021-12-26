import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
// import HttpClientModule
import { HttpClientModule } from '@angular/common/http'; 
//import FormsModule
import { FormsModule } from '@angular/forms';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
//import { UpdateSalespersonComponent } from './components/update-salesperson/update-salesperson.component';
//import { DeleteSalespersonComponent } from './components/delete-salesperson/delete-salesperson.component';
//import { SalespeopleComponent } from './components/salespeople/salespeople.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
