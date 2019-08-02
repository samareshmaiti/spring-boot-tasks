package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    //Define all the methods that are required

    public Track saveTrack(Track track);

    public Track getTrackById(int id);

    public List<Track> getAllTracks();

    public Track deleteTrackById(int id);

    public Track updateTrackById(Track track,int id);

    public List<Track> getTrackByName(String name);


}
