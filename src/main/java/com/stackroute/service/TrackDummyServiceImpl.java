package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("dev")
@Service
//This annotation defines a preference when multiple beans of the same type are present.
// The bean associated with the @Primary annotation will be used unless otherwise indicated.
//@Primary

//In Spring, @Qualifier means, which bean is qualify to autowired on a field.
@Qualifier("Dummy")
public class TrackDummyServiceImpl implements TrackService {
    TrackRepository trackRepository;

    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track Already There");
        }
        return trackRepository.save(track);
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track Not Found");
        }
        return trackRepository.findById(id).get();
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track deleteTrackById(int id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return optionalTrack.get();
    }

    @Override
    public Track updateTrackById(Track track, int id) {
        return null;
    }

    @Override
    public List<Track> getTrackByName(String name) {
        return trackRepository.getTrackByName(name);
    }
}
