import { Injectable } from '@angular/core';
import { TrainingSession } from './trainingSession';
import { TRAININGS } from './mock-trainings';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TraniningsService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  getTrainings(): Observable<TrainingSession[]>{
   // const trainingSessions= of(TRAININGS);
    //this.messageService.add('TrainingService: fetched trainings');
    //return trainingSessions;
    return this.http.get<TrainingSession[]>(this.trainingsUrl)
    .pipe(
      tap(_ => this.log('fetched trainingSessions')),
      catchError(this.handleError<TrainingSession[]>('getTrainings',[]))
    )
    ;
  }

  /** GET hero by id. Will 404 if id not found */
  getTraining(id: number): Observable<TrainingSession> {
    const url = `${this.trainingsUrl}/${id}`;
    return this.http.get<TrainingSession>(url).pipe(
     tap(_ => this.log(`fetched training id=${id}`)),
     catchError(this.handleError<TrainingSession>(`getTraining id=${id}`))
   );
  }

  /** PUT: update the hero on the server */
  updateTraining(trainingSession: TrainingSession): Observable<any> {
    const url = `${this.trainingsUrl}/${trainingSession.id}`;
    return this.http.put(url, trainingSession, this.httpOptions).pipe(
      tap(_ => this.log(`updated training id=${trainingSession.id}`)),
      catchError(this.handleError<any>('updateTraining'))
   );
  }
  /** POST: add a new hero to the server */
addTraining(trainingSession: TrainingSession): Observable<TrainingSession> {
  return this.http.post<TrainingSession>(this.trainingsUrl, trainingSession, this.httpOptions).pipe(
    tap((newHero: TrainingSession) => this.log(`added training w/ id=${newHero.id}`)),
    catchError(this.handleError<TrainingSession>('addTraining'))
  );
}
deleteTraining(id: number): Observable<TrainingSession> {
  const url = `${this.trainingsUrl}/${id}`;

  return this.http.delete<TrainingSession>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted training id=${id}`)),
    catchError(this.handleError<TrainingSession>('deleteTraining'))
  );
}
  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}
  private trainingsUrl = 'http://localhost:8080/trainings';
  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`TrainingsService: ${message}`);
  }
  constructor(private http: HttpClient, private messageService: MessageService) { }
}
