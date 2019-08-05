package com.stackroute.repository;
import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


//The central interface in Spring Data repository abstraction is Repository.
// It takes the the domain class to manage as well as the id type of the domain class as type arguments.
@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    List<Track> getTrackByName(String name);


}
