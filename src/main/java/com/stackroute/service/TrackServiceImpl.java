package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //implement of saveTrack() method
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId()))
        {
            throw new TrackAlreadyExistsException("Track is already exists");
        }
        if(trackRepository==null)
        {
            throw new TrackAlreadyExistsException("check connection to database");
        }
        return trackRepository.save(track);

    }

    //implement of getTrackById() method
    @Override
    public Track getTrackById(int id) {
        return trackRepository.findById(id).get();

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
       return  trackRepository.getTrackByName(name);
    }


}
