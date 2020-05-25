import { Component, OnInit } from '@angular/core';
import {AnnouncementService} from "../services/announcement.service";
import {UserService} from "../services/user.service";
import {Announcement} from "../entities/announcement";
import {CREATED_ANNOUNCEMENT_ID} from "../parameters";

@Component({
  selector: 'app-announcement-dashboard',
  templateUrl: './announcement-dashboard.component.html',
  styleUrls: ['./announcement-dashboard.component.css']
})
export class AnnouncementDashboardComponent implements OnInit {

  announcement: Announcement;
  announcements: Announcement[] = [];
  newAnnouncements: Announcement[] = [];
  editOnly = false;
  constructor(private announcementService: AnnouncementService, private userService: UserService) { }

  getRole() {
    return this.userService.user.role.name;
  }

  editAnn(announcement: Announcement) {
    this.announcement = announcement;
    this.editOnly = true;
  }
  ngOnInit(): void {
    this.getAll();
    this.getCreated();
  }
  getAll() {
    this.announcementService.getAll(this.userService.user.id).
    subscribe(res => {this.announcements = res},
              error => this.announcements = [] );
  }
  getNewAnnouncements() {
    return this.newAnnouncements.filter(ann => ann.statusId === CREATED_ANNOUNCEMENT_ID);
  }
  getCreated() {
    this.announcementService.getCreated().
    subscribe(res => {this.newAnnouncements = res},
      error => this.newAnnouncements = [] );
  }

}
