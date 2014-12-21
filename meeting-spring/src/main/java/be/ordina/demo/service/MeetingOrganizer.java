package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingOrganizer {

    private final MeetingRoomRepository meetingRoomRepository;

    private final MeetingRoomInitializer meetingRoomInitializer;

    @Autowired
    public MeetingOrganizer(MeetingRoomRepository meetingRoomRepository, MeetingRoomInitializer meetingRoomInitializer) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingRoomInitializer = meetingRoomInitializer;
    }

    @RequestMapping(value = "/meetingRooms")
    public List<MeetingRoom> getMeetingRooms() {
        if(!meetingRoomRepository.hasMeetingRooms()){
            meetingRoomInitializer.initializeMeetingRooms();
        }
        return meetingRoomRepository.getAllMeetingRooms();
    }


}
