import { Component, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import {MatSidenavModule, } from '@angular/material/sidenav';
import {MatListItem, MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatRippleModule} from '@angular/material/core';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatSidenavModule, MatListModule, MatIconModule, MatButtonModule, MatToolbarModule, RouterLink, MatRippleModule, MatButtonModule, MatListItem],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('ecommerce-dashboard');
  protected toggleMenu = signal(true);
}
