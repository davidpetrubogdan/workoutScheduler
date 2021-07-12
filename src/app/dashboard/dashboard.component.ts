import { Component, OnInit } from '@angular/core';
import { TrainingSession } from '../trainingSession';
import { TraniningsService } from '../traninings.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  trainings: TrainingSession[] = [];

  constructor(private trainingService: TraniningsService) { }

  ngOnInit() {
    this.getTrainings();
  }

  getTrainings(): void {
    this.trainingService.getTrainings()
      .subscribe(trainings => this.trainings = trainings.slice(1, 5));
  }
}