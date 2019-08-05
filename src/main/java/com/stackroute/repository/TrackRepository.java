package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//The central interface in Spring Data repository abstraction is Repository.
// It takes the the domain class to manage as well as the id type of the domain class as type arguments.
@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {


}
