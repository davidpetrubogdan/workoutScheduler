import { Component, OnInit } from '@angular/core';
import { TrainingSession } from '../trainingSession';
import { TraniningsService } from '../traninings.service';
import { MessageService } from '../message.service';
import { WeekDay } from '@angular/common';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-trainings',
  templateUrl: './trainings.component.html',
  styleUrls: ['./trainings.component.css']
})
export class TrainingsComponent implements OnInit {
 // days : string[] = [];
  closeResult?: string; 
  weekDays: string[] = ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"];
  typesOfTraining: string[] = ["HIIT", "CARDIO", "STRENGTH", "HYPERTROPHY"];
  trainingSessions: TrainingSession[] = [];
  constructor(private trainingService: TraniningsService, private messageService: MessageService,private modalService: NgbModal) { }
  selectedType?: string;
 
  getTrainings():void{
    this.trainingService.getTrainings().subscribe(trainingSessions => this.trainingSessions = trainingSessions);
    }
   // this.trainingSessions=this.trainingService.getTrainings();
  ngOnInit(): void {
    this.getTrainings();
    
  }
  private initDays():void{
  }
  add(hoursString: String, day: String, type?: String): void {
   // name = name.trim();
   const hours = +hoursString;
    if (!hours) { return; }
    this.trainingService.addTraining({ hours ,day, type} as TrainingSession)
      .subscribe(trainingSession => {
        this.trainingSessions.push(trainingSession);
      });
  }

  delete(trainingSession: TrainingSession): void {
    this.trainingSessions = this.trainingSessions.filter(h => h !== trainingSession);
    this.trainingService.deleteTraining(trainingSession.id).subscribe();
  }

  open(content: any, trainingSesion: TrainingSession) {  
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {  
      this.closeResult = `Closed with: ${result}`;  
      if (result === 'yes') {  
        this.delete(trainingSesion);  
      }  
    }, (reason) => {  
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;  
    });  
  }  
  
  private getDismissReason(reason: any): string {  
    if (reason === ModalDismissReasons.ESC) {  
      return 'by pressing ESC';  
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {  
      return 'by clicking on a backdrop';  
    } else {  
      return `with: ${reason}`;  
    }  
  }

}
// trainingSession: TrainingSession = {
  //   id: 1,
  //   hours: 2,
  //   type: 'STRENGTH'};
 // selectedTrainingSession?:TrainingSession;
  // onSelect(trainingSession:TrainingSession):void{
  //   this.selectedTrainingSession=trainingSession;
  //   this.messageService.add(`TrainingsComponent: Selected training id=${trainingSession.id}`);

  // }