package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("dev")
@Service
//This annotation defines a preference when multiple beans of the same type are present.
// The bean associated with the @Primary annotation will be used unless otherwise indicated.
//@Primary

//In Spring, @Qualifier means, which bean is qualify to autowired on a field.
@Qualifier("Dummy")
public class TrackDummyServiceImpl implements TrackService {

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public List<Track> getAllTracks() {
        return null;
    }

    @Override
    public Track deleteTrackById(int id) {
        return null;
    }

    @Override
    public Track updateTrackById(Track track, int id) {
        return null;
    }

    @Override
    public List<Track> getTrackByName(String name) {
        return null;
    }
}
