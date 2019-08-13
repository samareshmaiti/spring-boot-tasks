package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    /* @Before annotation is used on a method
       code to run before each test case. i.e it runs before each test execution.
            */
    @Before
    public void setUp() {
        track = new Track();
        track.setId(10);
        track.setName("track name");
        track.setComment("comment1");

    }

    /*  @After annotation is used on a method to run after each test case.
        These methods will run even if any exceptions are thrown in the test case or in the case
        of assertion failures.
        In the tear down method ,object is initialized with null so that obj is destroyed
        */
    @After
    public void tearDown() {

        trackRepository.deleteAll();
        track = null;
    }

    //This test case for check whether the given track details is being saved
    @Test
    public void givenTrackShouldReturnSavedTrack() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(10, fetchTrack.getId());

    }

    //This test case for checking whether a given track id already exists and return TrackAlreadyExistsException
    @Test
    public void givenTrackShouldNotReturnSavedTrack() {
        Track testUser = new Track(10, "track name", "comment1");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testUser, track);
    }

    //This method to return all the tracks that matches with a certain name
    @Test
    public void givenInputShouldReturnAllTracks() {
        Track u = new Track(10, "track name1", "comment2");
        Track u1 = new Track(11, "track name2", "comment3");
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("track name1", list.get(0).getComment());
    }


    @Test
    public void givenTrackNameShouldReturnProperId() {
        trackRepository.save(track);
        Track trackDetails = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(99, trackDetails.getId());
    }

    @Test
    public void givenTrackNameShouldNotReturnCorrectId() {
        trackRepository.save(track);
        Track trackDetails = trackRepository.findById(track.getId()).get();
        Assert.assertNotEquals(3, trackDetails.getId());
    }

    //This method to check whether to delete a track by a given track id
    @Test
    public void givenTrackIdShouldReturnDeletedTrack() {
        trackRepository.save(track);
        trackRepository.deleteById(track.getId());
        Assert.assertEquals(10, track.getId());

    }

    @Test
    public void givenTrackDetailsShouldUpdatePreviousTrackDetails() {
        trackRepository.save(track);
        trackRepository.deleteById(track.getId());
        track.setId(10);
        track.setName("maiti");
        track.setComment("comment");
        trackRepository.save(track);
        Track updatedTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(track, updatedTrack);
    }


}

