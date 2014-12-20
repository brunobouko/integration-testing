package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.repo.MeetingRoomRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingOrganizer {

    private final MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public MeetingOrganizer(MeetingRoomRepository meetingRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
    }

    @RequestMapping("/meetingRooms")
    @JsonView
    public List<MeetingRoom> getMeetingRooms() {
        return meetingRoomRepository.getAllMeetingRooms();
    }


}
