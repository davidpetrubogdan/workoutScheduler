import { Component, OnInit } from '@angular/core';
import { TrainingSession } from '../trainingSession';
import { TraniningsService } from '../traninings.service';
import { Location } from '@angular/common';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  styleUrls: ['./create-training.component.css']
})
export class CreateTrainingComponent implements OnInit {

  weekDays: string[] = ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"];
  typesOfTraining: string[] = ["HIIT", "CARDIO", "STRENGTH", "HYPERTROPHY"];

  trainingForm = new FormGroup({
    hour: new FormControl('', [Validators.required, Validators.min(1) ]),
    type: new FormControl('', Validators.required),
    day: new FormControl('', Validators.required),
  })

  constructor(private trainingService: TraniningsService, private location: Location) { }
  selectedType?: string;

  onSubmit() {
    console.log(this.trainingForm.value);
    const hours = this.trainingForm.value.hour;
    const day = this.trainingForm.value.day;
    const type = this.trainingForm.value.type;
    this.trainingService.addTraining({ hours ,day, type} as TrainingSession)
       .subscribe(trainingSession => {
         this.location.back();
       //  this.trainingSessions.push(trainingSession);
       });
  }

  ngOnInit(): void {
  }
  add(hoursString: String, day: String, type?: String): void {
    const hours = +hoursString;
     if (!hours || hours<=0) { console.log("The duration must be a number");return; }
     this.trainingService.addTraining({ hours ,day, type} as TrainingSession)
       .subscribe(trainingSession => {
         this.location.back();
       //  this.trainingSessions.push(trainingSession);
       });
   }
}
