import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "../user.service";
import {Observable, of} from "rxjs";
import {Question} from "../entities/question";
import {catchError} from "rxjs/operators";
import {Option} from "../entities/option";
import {DefaultOption} from "../entities/default-option";
import {SequenceOption} from "../entities/sequence-option";

@Injectable({
  providedIn: 'root'
})
export class OptionService {
  private optionsUrl = 'api/option';  // URL to web api
  constructor(private http: HttpClient, private userService: UserService) {
  }

  getOptions(question_id): Observable<Option[]> {
    return this.http.get<Option[]>(`api/options/${question_id}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<Option[]>('getOptions', []))
      );
  }

  getDefaultOptions(question_id): Observable<DefaultOption[]> {
    return this.http.get<DefaultOption[]>(`api/default_options/${question_id}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<DefaultOption[]>('getDefaultOptions', []))
      );
  }

  getSequenceOptions(question_id): Observable<SequenceOption[]> {
    return this.http.get<SequenceOption[]>(`api/sequence_options/${question_id}`, { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
      .pipe(
        catchError(this.handleError<SequenceOption[]>('getSequenceOptions', []))
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


