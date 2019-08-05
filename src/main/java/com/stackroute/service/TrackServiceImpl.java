package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Profile("prod")
@Service
//@Primary
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //implement of saveTrack() method
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Track savedTrack = null;
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track is already exists");
        } else {
            savedTrack = trackRepository.save(track);
            if (savedTrack == null) {
                throw new TrackAlreadyExistsException("User already exist");
            }
        }

        return savedTrack;

    }

    //implement of getTrackById() method
    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track not found ");
        }
        Track retrievedTrack = trackRepository.findById(id).get();
        return retrievedTrack;


    }

    //implement of getAllTracks() method
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();

    }

    //implement of deleteTrackById() method
    @Override
    public Track deleteTrackById(int id) {

        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return optionalTrack.get();

    }

    //implement of updateTrackById() method
    @Override
    public Track updateTrackById(Track track, int id) {
        trackRepository.deleteById(id);
        return trackRepository.save(track);
    }

    //implement of the getTrackByName() method
    @Override
    public List<Track> getTrackByName(String name) {
        return trackRepository.getTrackByName(name);
    }


}
