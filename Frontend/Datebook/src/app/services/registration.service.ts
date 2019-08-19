import { Injectable } from '@angular/core';
import { HttpClient } from 'selenium-webdriver/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  constructor(
    private readonly http: HttpClient
  ) {}

  registration(data: registrationData) {

  }
}
