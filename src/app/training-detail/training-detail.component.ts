import { Component, OnInit,Input } from '@angular/core';
import { TrainingSession } from '../trainingSession';
import { TraniningsService } from '../traninings.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
@Component({
  selector: 'app-training-detail',
  templateUrl: './training-detail.component.html',
  styleUrls: ['./training-detail.component.css']
})
export class TrainingDetailComponent implements OnInit {
  @Input() trainingSession?:TrainingSession;
  weekDays: string[] = ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"];
  typesOfTraining: string[] = ["HIIT", "CARDIO", "STRENGTH", "HYPERTROPHY"];
  constructor(private trainingService : TraniningsService,
    private route: ActivatedRoute,
    private location: Location) { }

  save(): void{
    if(this.trainingSession){
      const hours = this.trainingSession.hours;
     if (!hours || hours<=0) { console.log("The duration must be a number");return; }
      this.trainingService.updateTraining(this.trainingSession)
        .subscribe(()=> this.goBack());
    }

  }
  ngOnInit(): void {
    this.getTraining();
  }
  getTraining(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.trainingService.getTraining(id)
      .subscribe(trainingSession => this.trainingSession = trainingSession);
  }

  goBack(): void {
    this.location.back();
  }
}
