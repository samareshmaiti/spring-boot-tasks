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
    //private Object Track;

    /* @Before annotation is used on a method
   code to run before each test case. i.e it runs before each test execution.
        */
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

    /*  @After annotation is used on a method to run after each test case.
           These methods will run even if any exceptions are thrown in the test case or in the case
           of assertion failures.
           In the tear down method ,object is initialized with null so that obj is destroyed
           */
    @After
    public void tearDown() {
        this.trackServiceImpl = null;
        this.track = null;
        this.list = null;
    }

    //This test case for check whether the given track details is being saved
    @Test
    public void givenTrackDetailsShouldSaveTracks() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track saveTrack = trackRepository.save(track);
        Assert.assertEquals(track, saveTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    //This test case for checking whether a given track id already exists and return TrackAlreadyExistsException
    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTrackDetailsShouldReturnTrackAlreadyExistsException() throws TrackAlreadyExistsException {
        when(trackRepository.save(track)).thenReturn(null);
        Track saveTrack = trackServiceImpl.saveTrack(track);
        Assert.assertEquals(track, saveTrack);
        verify(trackRepository, times(1)).save(track);


    }

    //This method to check whether the given track name exists or not
    @Test(expected = TrackNotFoundException.class)
    public void givenTrackNameShouldReturnTrackNotFoundException() throws TrackNotFoundException {
        trackServiceImpl.getTrackByName("track");
    }

    //This method to return all the tracks that matches with a certain name
    @Test
    public void givenInputShouldReturnAllTrackDetails() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackRepository.findAll();
        Assert.assertEquals(list, userlist);
        verify(trackRepository, times(1)).findAll();
    }

    //This method to return a track details matching to the given track id
    @Test
    public void givenTrackIdShouldReturnTrackDetails() throws TrackNotFoundException {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findById(10)).thenReturn(Optional.of(track));
        Optional<Track> getTrack = trackRepository.findById(10);
        Assert.assertEquals(track, getTrack.get());
        verify(trackRepository, times(1)).findById(1);
    }

    @Test(expected = TrackNotFoundException.class)
    public void givenNotExistingTrackIdShouldReturnTrackNotFoundException() throws TrackNotFoundException {
        trackServiceImpl.getTrackById(1);
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
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        track.setComment("comment2");
        Track updatedTrack = new Track(10, "track name2", "comment2");
        Assert.assertEquals(updatedTrack, track);
        verify(trackRepository, times(0)).findById(track.getId());

    }

    //This method to check whether to delete a track by a given track id
    @Test
    public void givenTrackIdShouldReturnTheDeletedTrack() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        trackServiceImpl.deleteTrackById(track.getId());
        Assert.assertEquals(track, trackServiceImpl.getTrackById(track.getId()));
        verify(trackRepository, times(1)).deleteById(track.getId());
    }


}