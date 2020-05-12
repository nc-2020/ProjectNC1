import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Quiz} from "../entities/quiz";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private quizzesUrl = 'http://localhost:8080/api/quiz';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getQuiz(quizId): Observable<Quiz> {
    return  this.http.get<Quiz>(this.quizzesUrl + '/' + quizId, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz>('getQuiz')
        ));
  }
  getCreatedQuizzes(): Observable<Quiz[]> {
    return  this.http.get<Quiz[]>(this.quizzesUrl + '/created', { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getCreatedQuizzes',[])
        ));
  }
  getQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizzesUrl,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getQuizzes', []))
      );
  }
  getAllUserQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizzesUrl + '/user/' + this.userService.user.id + '/all',{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getUserAllQuizzes', []))
      );
  }

    approveQuiz(quiz: Quiz): Observable<any> {
    return  this.http.post<Quiz>(this.quizzesUrl + '/approve', quiz, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
  }

  getUserQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizzesUrl + "/user/" + this.userService.user.id,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getUserQuizzes', []))
      );
  }
  /** POST: add a new quiz to the server */
  createQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>(this.quizzesUrl, quiz, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<Quiz>('createQuiz'))
    );
  }

  deleteQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.delete<Quiz>(this.quizzesUrl + '/' + quiz.id,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<any>('deleteQuiz'))
    );
  }

  searchQuizzes(term: string): Observable<Quiz[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Quiz[]>(`${this.quizzesUrl}/search/${term}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<Quiz[]>('searchQuizzes', []))
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
