import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Quiz} from "../entities/quiz";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {Category} from "../entities/category";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoriesUrl = 'http://localhost:8080/api/category';


  constructor(private http: HttpClient, private userService: UserService) {
  }

  getCategory(category: Category) {

    return this.http.get<Category>(this.categoriesUrl + `/get/${category.id}`).pipe(

      catchError(this.handleError<any>('getCategory'))
    );
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('http://localhost:8080/api/categories', { headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<any>('getCategory'))
    );
  }

  updateCategory(category: Category) {
    return this.http.put<Quiz>(this.categoriesUrl + '/update', category).pipe(
      catchError(this.handleError<any>('editCategory'))
    );
  }
  createCategory(category: Category) {
    return this.http.post<Category>(this.categoriesUrl + '/create', category).pipe(
      catchError(this.handleError<any>('createCategory'))
    );
  }

  deleteCategory(category: Category) {
    return this.http.delete<Category>(this.categoriesUrl + `/delete/${category.id}`).pipe(
      catchError(this.handleError<any>('deleteCategory'))
    );
  }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
}
