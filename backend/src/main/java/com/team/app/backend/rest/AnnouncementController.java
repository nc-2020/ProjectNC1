package com.team.app.backend.rest;

import com.team.app.backend.persistance.model.Announcement;
import com.team.app.backend.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @PostMapping("/create")
    public ResponseEntity createAnnouncement(@RequestBody Announcement announcement) {
        Map<String, String> response = new HashMap<>();
        try {
            announcementService.createAnnouncement(announcement);
        }
        catch(DataAccessException sqlEx){
            response.put("message","There is a problem while creating announcement");
            ResponseEntity.badRequest().body(response);
        }
        response.put("message","Created!");
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/created")
    public ResponseEntity getCreated() {
        List<Announcement> announcementList;
        try {
            announcementList = announcementService.getCreated();
        }
        catch(DataAccessException sqlEx){

            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(announcementList);

    }
    @GetMapping("/all")
    public ResponseEntity getAll() {
        List<Announcement> announcementList;
        try {
            announcementList = announcementService.getAll();
        }
        catch(DataAccessException sqlEx){

            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(announcementList);

    }
    @PostMapping("/approve")
    public ResponseEntity approve(@RequestBody Announcement announcement) {
        try {
            announcementService.approve(announcement);
        }
        catch(DataAccessException sqlEx){

            return ResponseEntity.badRequest().body(sqlEx.toString());
        }
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    public ResponseEntity updateAnnouncement(@RequestBody Announcement announcement) {
        Map<String, String> model = new HashMap<String, String>();
        try {
            announcementService.updateAnnouncement(announcement);
        }
        catch(DataAccessException sqlEx){
            model.put("message","There is a problem while updating announcement");
            return ResponseEntity.badRequest().body(model);
        }
        model.put("message","Updated");
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity updateAnnouncement(@PathVariable("id") long id) {
        Map<String, String> model = new HashMap<String, String>();
        try {
            announcementService.deleteAnnouncement(id);

        }
        catch(DataAccessException sqlEx){
           model.put("message","There is a problem while updating announcement");
            return ResponseEntity.badRequest().body(model);
        }
        model.put("message","Updated");
        return ResponseEntity.ok(model);
    }
}
