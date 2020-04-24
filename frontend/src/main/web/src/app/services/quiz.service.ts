import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {Quiz} from "../entities/quiz";
import {UserService} from "../user.service";

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  constructor(private http: HttpClient, private userService: UserService) {
  }
  getQuiz(quiz: Quiz) {
    return this.http.get<Quiz>(`api/quiz/get/${quiz.id}`).pipe(
      catchError(this.handleError<any>('getQuiz'))
    );
  }
  updateQuiz(quiz: Quiz) {
    return this.http.put<Quiz>('api/quiz/update', quiz).pipe(
      catchError(this.handleError<any>('editQuiz'))
    );
  }
  createQuiz(quiz: Quiz) {
    quiz.userId = this.userService.user.id;
    return this.http.post<Quiz>('api/quiz/create', quiz).pipe(
      catchError(this.handleError<any>('createQuiz'))
    );
  }

  deleteQuiz(quiz: Quiz) {
    return this.http.delete<Quiz>(`api/quiz/delete/${quiz.id}`).pipe(
      catchError(this.handleError<any>('deleteQuiz'))
    );
  }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
}
