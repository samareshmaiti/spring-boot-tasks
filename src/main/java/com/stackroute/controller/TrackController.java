package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "api/v1/")
public class TrackController {

    private TrackService trackService;
    private TrackRepository repository;

    @Autowired
//implementation of the @Qualifier annotation
    public TrackController(@Qualifier("Dummy") TrackService trackService1) {
        this.trackService = trackService1;
    }

    //Use of PostMapping to put the values in the database
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    //Use GetMapping to find the track searched by id
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;

        Track retrivedTrack = trackService.getTrackById(id);
        return new ResponseEntity<>(retrivedTrack, HttpStatus.OK);


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
        return new ResponseEntity<>(track1, HttpStatus.OK);
    }

    //Use of GetMapping to find out the names of the tracks that are matches to given input
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(trackService.getTrackByName(name), HttpStatus.OK);
    }

}


