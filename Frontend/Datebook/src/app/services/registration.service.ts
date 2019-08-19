import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import * as constants from '../constants';
import { RegistrationData } from '../models';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  constructor(
    private readonly http: HttpClient
  ) {}

  registration(data: RegistrationData): Observable<any> {
    const address = `${environment.apiUrl}/${constants.authorisationPrefix}/${constants.registrationEndpoint}`;

    const body = {
      ...data,
    };

    return this.http.post(address, body);
  }
}
