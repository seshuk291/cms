import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { Observable, throwError } from "rxjs";
import { catchError, retry } from "rxjs/operators";
import { UsersListReponse } from "../models/users-response";

@Injectable({providedIn: 'root'})
export class UserService {
    private apiUrl = environment.apiUrl;
    private http = inject(HttpClient);

    public getAllUsers(): Observable<UsersListReponse> {
        return this.http.get<UsersListReponse>(this.apiUrl + "/users")
            .pipe(
                retry(1),
                catchError(this.handleError)
            );
    }

    private handleError(error: HttpErrorResponse) {
        let errorMessage = 'An unknown error occurred!';
        if (error.error instanceof ErrorEvent) {
            // Client-side errors
            errorMessage = `Error: ${error.error.message}`;
        } else {
            // Server-side errors
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        console.error('UserService Error:', errorMessage);
        return throwError(() => new Error(errorMessage));
    }
}