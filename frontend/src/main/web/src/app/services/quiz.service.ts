import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Quiz} from "../entities/quiz";
import {UserService} from "./user.service";
import {Session} from "../entities/session";
import {SessionStats} from "../entities/session-stats";
import {UserSessionResult} from "../entities/UserSessionResult";

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private quizzesUrl = 'http://localhost:8080/api/quiz';  // URL to web api
  private userId;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private userService: UserService) {
    this.userId = this.userService.user.id;
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

  getSession(quizId: number): Observable<Session> {
    return this.http.get<Session>(this.quizzesUrl + `/play/${this.userId}/${quizId}`,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Session>('getSession')
        ));
  }



  startSession(sessionId) {
    return this.http.post(this.quizzesUrl + `/start/${sessionId}`, sessionId,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError('startSession')
        ));
  }

  joinSession(accessCode) {
    return this.http.get(this.quizzesUrl + `/join/?user_id=${this.userId}&access_code=` + accessCode,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError('joinSession')
        ));
  }

  getStatsSession(sessionId): Observable<SessionStats[]> {
    return this.http.get<SessionStats[]>(this.quizzesUrl + `/stats/${sessionId}`,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<SessionStats[]>('getStatsSession')
        ));
  }

  sendSessionStats(userSessionResult: UserSessionResult) {
    return this.http.post<UserSessionResult>(this.quizzesUrl + `/finish`, userSessionResult, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<UserSessionResult>('sendSessionStats'))
    );
  }




  getQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizzesUrl + "/approved/" + this.userId,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getQuizzes', []))
      );
  }

  getUserQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizzesUrl + "/user/" + this.userId,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getUserQuizzes', []))
      );
  }

  getFavoriteQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizzesUrl + '/favorite/' + this.userId,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getFavoriteQuizzes', []))
      );
  }

  getSuggestionsQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.quizzesUrl + '/suggestion/' + this.userId,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Quiz[]>('getSuggestionsQuizzes', []))
      );
  }

  /** POST: add a new quiz to the server */

  approveQuiz(quiz: Quiz): Observable<any> {
    return  this.http.post<Quiz>(this.quizzesUrl + '/approve', quiz, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})

  }

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
