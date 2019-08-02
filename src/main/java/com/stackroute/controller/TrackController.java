package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/")
public class TrackController {
    private TrackService trackService;
    private TrackRepository repository;

    @Autowired
    public TrackController(TrackService trackService1) {
        this.trackService = trackService1;
    }
//Use of PostMapping to put the values in the database
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
//Use GetMapping to find the track searched by id
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        Track retriveUser = trackService.getTrackById(id);
        return new ResponseEntity<>(retriveUser, HttpStatus.OK);
    }
//Use GetMapping to get all the trackes in the datadase with details
    @GetMapping("track")
    public ResponseEntity<?> getAllTrack() {
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }
//Use DeleteMapping to delete a particular track given by id
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) {
        Optional<Track> trackRemoved = Optional.of(trackService.deleteTrackById(id));
        return new ResponseEntity<>(trackRemoved, HttpStatus.OK);
    }
    //Use PatchMapping to update the element in database
    @PatchMapping("/track/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track, @PathVariable("id") int id) {
        Track track1 = trackService.updateTrackById(track, id);
        return new ResponseEntity < > (track1, HttpStatus.OK);
    }

}


