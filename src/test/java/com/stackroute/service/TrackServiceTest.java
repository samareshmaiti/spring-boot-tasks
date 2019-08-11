package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    private Track track;

    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceImpl trackServiceImpl;
    private List<Track> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(10);
        track.setName("track name");
        track.setComment("comment1");
        list = new ArrayList<>();
        list.add(track);


    }

    @After
    public void tearDown() {
        this.trackServiceImpl = null;
        this.track = null;
        this.list = null;
    }

    @Test
    public void givenTrackDetailsShouldSaveTracks() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track saveTrack = trackRepository.save(track);
        Assert.assertEquals(track, saveTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTrackDetailsShouldReturnTrackAlreadyExistsException() throws TrackAlreadyExistsException {
        when(trackRepository.save(track)).thenReturn(null);
        Track saveTrack = trackServiceImpl.saveTrack(track);
        Assert.assertEquals(track, saveTrack);
        verify(trackRepository, times(1)).save(track);


    }

    @Test
    public void givenInputShouldReturnAllTrackDetails() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackRepository.findAll();
        Assert.assertEquals(list, userlist);
        verify(trackRepository, times(1)).findAll();
    }

    @Test
    public void givenTrackIdShouldReturnTrackDetails() throws TrackNotFoundException {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findById(10)).thenReturn(Optional.of(track));
        Optional<Track> getTrack = trackRepository.findById(10);
        Assert.assertEquals(track, getTrack.get());
        verify(trackRepository, times(1)).findById(1);
    }
        @Test
    public void givenTrackNameShouldReturnTrackDetails() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.getTrackByName("track name")).thenReturn((List<Track>) track);
        Track getTrack = (Track) trackServiceImpl.getTrackByName("track name");
        Assert.assertEquals(track, getTrack);

        verify(trackRepository, times(1)).getTrackByName("track name");
    }

    @Test
    public void givenTrackDetailsShouldUpdateThePreviousDetails() {
    trackRepository.save(track);
     when(trackRepository.findById(track.getId())).thenReturn(Optional.ofNullable(track));

    }

}