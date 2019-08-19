import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { LoginService, RegistrationService } from '../../services';

@Component({
  selector: 'app-authorisation',
  templateUrl: './authorisation.component.html',
  styleUrls: ['./authorisation.component.scss']
})
export class AuthorisationComponent implements OnInit {
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  registrationForm = new FormGroup({
    name: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
    role: new FormControl('user')
  })

  constructor(
    private readonly loginService: LoginService,
    private readonly registrationService: RegistrationService
  ) { }

  ngOnInit() {
  }

  login() {
    this.loginService.login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe(
        data => {
          console.log('success');
        },
        error => {
          console.log(error);
        }
      )
  }

  registration() {
    this.registrationService.registration(this.registrationForm.value)
      .subscribe(
        data => {
          console.log('registration complete')
        },
        error => {
          console.log(error);
        }
      )
  }
}
