import { Component, OnInit } from '@angular/core';
import {Announcement} from "../entities/announcement";
import {UserService} from "../user.service";
import {AnnouncementService} from "../announcement.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-announcement-edit',
  templateUrl: './announcement-edit.component.html',
  styleUrls: ['./announcement-edit.component.css']
})
export class AnnouncementEditComponent implements OnInit {


  announcement: Announcement = {
    id: '1',
    title: 'New epic big super announcement',
    text: 'Some quick example text to build on the card title and make up the bulk of the card`s content.',
    date: '23.03.2020',
    image: 'https://cdn.pixabay.com/photo/2020/04/09/12/28/dog-5021242_1280.jpg',
    userId: '1'
  };
  error = ''
  message = '';

  announcementForm: FormGroup;

  constructor(private fb: FormBuilder, private announcementService: AnnouncementService, private userService: UserService) { }

  ngOnInit(): void {
    this.announcementForm = this.fb.group({
      title: [this.announcement.title, [Validators.required, Validators.minLength(3)]],
      text: [this.announcement.text, [Validators.required, Validators.minLength(3)]],
      image: [this.announcement.image],

    });
  }

}
