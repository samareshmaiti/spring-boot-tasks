package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//The central interface in Spring Data repository abstraction is Repository.
// It takes the the domain class to manage as well as the id type of the domain class as type arguments.


@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    @Query("select track from Track track where track.name like ?1")
    List<Track> getTrackByName(String name);

}
