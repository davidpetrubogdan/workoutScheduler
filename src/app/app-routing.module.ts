import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TrainingsComponent } from './trainings/trainings.component';
import { TrainingDetailComponent } from './training-detail/training-detail.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateTrainingComponent } from './create-training/create-training.component';
const routes: Routes = [
  {path:'trainings', component: TrainingsComponent},
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path:'dashboard', component: DashboardComponent},
  {path: 'edit/:id', component: TrainingDetailComponent},
  {path: 'add', component: CreateTrainingComponent}
];
imports: [ RouterModule.forRoot(routes) ]
exports: [RouterModule]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
