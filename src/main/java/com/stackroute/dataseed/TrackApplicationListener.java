//package com.stackroute.dataseed;
//
//import com.stackroute.domain.Track;
//import com.stackroute.repository.TrackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
////This class always provide data when we are running the application each time instead of putting data
//// each time after running of our project
//@Component
////@Qualifier("TrackServiceImpl")
//public class TrackApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
//    private TrackRepository trackRepository;
//
//    //Autowire of TrackRepository and making constructor of TrackApplicationListener class
//
//    @Autowired
//    public TrackApplicationListener(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
//
//    //putting data into database using Track object
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        System.out.println("Context application listerer Received");
//        Track track1 = new Track(1, "hello", "first");
//        trackRepository.save(track1);
//        Track track2 = new Track(2, "hello2", "first2");
//        trackRepository.save(track1);
//        Track track3 = new Track(3, "hello3", "first3");
//        trackRepository.save(track3);
//        Track track4 = new Track(4, "hello4", "first4");
//        trackRepository.save(track4);
//    }
//}
