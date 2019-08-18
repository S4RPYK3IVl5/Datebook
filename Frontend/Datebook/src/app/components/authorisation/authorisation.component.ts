import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-authorisation',
  templateUrl: './authorisation.component.html',
  styleUrls: ['./authorisation.component.scss']
})
export class AuthorisationComponent implements OnInit {

  type = 'login';

  constructor() { }

  ngOnInit() {
  }

}
