import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { LoginService } from '../../services';

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

  constructor(
    private readonly loginService: LoginService
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
}
