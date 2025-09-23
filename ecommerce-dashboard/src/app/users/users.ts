import { Component, OnInit, ViewChild, AfterViewInit, OnDestroy, signal } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { UserService } from './user.service';
import { User } from '../models/user';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-users',
  imports: [MatTableModule, MatPaginatorModule, MatProgressSpinnerModule, MatButtonModule, CommonModule],
  templateUrl: './users.html',
  styleUrl: './users.scss'
})
export class Users implements OnInit, AfterViewInit, OnDestroy {
  displayedColumns: string[] = ['id', 'userName', 'firstName', 'lastName', 'email'];
  dataSource = new MatTableDataSource<User>();
  isLoading = signal(false);
  error: string | null = null;
  private subscription: Subscription = new Subscription();

  @ViewChild(MatPaginator) paginator?: MatPaginator;

  constructor(private userService: UserService) {}

  ngAfterViewInit() {
    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
    }
  }

  ngOnInit() {
    this.loadUsers();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  private loadUsers() {
    this.isLoading.set(true);
    this.error = null;
    
    const usersSub = this.userService.getAllUsers().subscribe({
      next: (users) => {
        console.log("users", users);
        this.dataSource.data = users.data as User[];
        this.isLoading.set(false);
      },
      error: (error) => {
        console.error("error loading users", error);
        this.error = 'Failed to load users. Please try again.';
        this.isLoading.set(false);
      }
    });
    this.subscription.add(usersSub);
  }
}
