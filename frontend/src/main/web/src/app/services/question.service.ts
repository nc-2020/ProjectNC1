import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";
import {Question} from "../entities/question";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {QuizService} from "./quiz.service";
@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) {
  }
  getQuestion(question: Question) {
    return this.http.get<Question>(`api/question/get/${question.id}`).pipe(
      catchError(this.handleError<any>('getQuestion'))
    );
  }

  updateQuestion(question: Question) {
    return this.http.put<Question>('api/question/update', question).pipe(
      catchError(this.handleError<any>('editQuestion'))
    );
  }
  createQuestion(question: Question, quiz_id) {
    question.quiz_id = quiz_id;
    return this.http.post<Question>('api/question/create', question).pipe(
      catchError(this.handleError<any>('createQuestion'))
    );
  }

  deleteQuestion(question: Question) {
    return this.http.delete<Question>(`api/question/delete/${question.id}`).pipe(
      catchError(this.handleError<any>('deleteQuestion'))
    );
  }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
}
