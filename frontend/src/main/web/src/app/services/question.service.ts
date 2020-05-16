import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Question} from "../entities/question";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private questionsUrl = 'http://localhost:8080/api/question/';  // URL to web api
  // private questionsUrl = '/api/question/';  // URL to web api
  private apiUrl = 'http://localhost:8080/api';

  // private apiUrl = '/api';


  constructor(private http: HttpClient, private userService: UserService) {
  }

  getQuestions(quiz_id): Observable<Question[]> {
    return this.http.get<Question[]>(this.apiUrl + `/questions/${quiz_id}`, { headers: new HttpHeaders()

        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Question[]>('getQuestions', []))
      );
  }

  getQuestion(question_id): Observable<Question> {
    return  this.http.get<Question>(this.questionsUrl + question_id, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
  }

  /** POST: add a new question to the server */
  createQuestion(question: Question): Observable<Question> {
    console.table(question);
    return this.http.post<Question>(this.questionsUrl  + question.type.id, question, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<Question>('createQuestion'))
    );
  }

  deleteQuestion(question: Question | number): Observable<Question> {
    const id = typeof question === 'number' ? question : question.id;
    const url = `${this.questionsUrl}/question/${id}`;
    return this.http.delete<Question>(url, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<Question>('deleteQuestion'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
