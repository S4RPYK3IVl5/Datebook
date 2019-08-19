import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import * as constants from '../constants';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(
    private readonly http: HttpClient
  ) {}

  login(username: string, password: string): Observable<any> {
    const address = `${environment.apiUrl}/${constants.authorisationPrefix}/${constants.loginEndpoint}`;

    const body = {
      username,
      password
    };

    return this.http.post(address, body);
  }
}
