import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Announcement} from "../entities/announcement";
import {AnnouncementService} from "../services/announcement.service";
import {UserService} from "../services/user.service";
import {User} from "../entities/user";

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {

  @Input()
  announcement: Announcement ;
  @Output()
  onChanged = new EventEmitter<Announcement>();
  error = '';
  message = '';


  constructor(private announcementService: AnnouncementService, private userService: UserService) { }

  getRole() {
    return this.userService.user.role.name;
  }
  edit() {
    this.onChanged.emit(this.announcement);
  }
  delete() {
    this.announcementService.deleteAnnouncement(+this.announcement.id).
    subscribe(resp => {window.location.reload()},
        error => {this.error = error.message});
  }
  ngOnInit(): void {
  }
  approve(approved: boolean) {
    this.announcement.statusId = approved ? 2 : 0;
    this.announcementService.approve(this.announcement).
    subscribe(resp => {},
      error => {this.announcement.statusId = 1 ; this.error = error.message;})

  }

}
