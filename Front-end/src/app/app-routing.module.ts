import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Components
import { LoginComponent } from './login/login.component';
import { AdminDashboardComponent } from './admin/admindashboard/admindashboard.component';
import { RelatorioComponent } from './relatorio/relatorio.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'relatorio', component: RelatorioComponent }

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
