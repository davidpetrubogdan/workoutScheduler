import { Component, OnInit } from '@angular/core';
import { TrainingSession } from '../trainingSession';
import { TraniningsService } from '../traninings.service';
import { Location } from '@angular/common';
@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  styleUrls: ['./create-training.component.css']
})
export class CreateTrainingComponent implements OnInit {

  weekDays: string[] = ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"];
  typesOfTraining: string[] = ["HIIT", "CARDIO", "STRENGTH", "HYPERTROPHY"];
  //trainingSessions: TrainingSession[] = [];
  constructor(private trainingService: TraniningsService, private location: Location) { }
  selectedType?: string;

  ngOnInit(): void {
  }
  add(hoursString: String, day: String, type?: String): void {
    // name = name.trim();
    const hours = +hoursString;
     if (!hours) { console.log("The duration must be a number");return; }
     this.trainingService.addTraining({ hours ,day, type} as TrainingSession)
       .subscribe(trainingSession => {
         this.location.back();
       //  this.trainingSessions.push(trainingSession);
       });
   }
}
