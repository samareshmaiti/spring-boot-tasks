package com.stackroute.dataseed;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//This class is to implement the commandline dataseed implementation
@Component
public class CommandLineTrackApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    private TrackRepository trackRepository;

    //Making constructor and autowiring the TrackRepository class
    @Autowired
    public CommandLineTrackApplicationListener(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //putting values and passing the Track object
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("Context Event Received");
        Track track1 = new Track(5, "hi1", "second1");
        trackRepository.save(track1);
        Track track2 = new Track(6, "hi2", "second2");
        trackRepository.save(track1);
        Track track3 = new Track(7, "hi3", "second3");
        trackRepository.save(track3);
        Track track4 = new Track(8, "hi4", "second4");
        trackRepository.save(track4);
    }
}
