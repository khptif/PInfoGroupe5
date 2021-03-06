import { Component, Inject, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
  	public auth: AuthService,
	 @Inject(DOCUMENT) private doc: Document
  ) {}

  ngOnInit(): void {}

  loginWithRedirect(): void {

    this.auth.loginWithRedirect();

  }

  signupWithRedirect(): void {
    this.auth.loginWithRedirect({ screen_hint: 'signup' });
	}

  logout(): void {
    this.auth.logout({ returnTo: this.doc.location.origin });
  }
}


