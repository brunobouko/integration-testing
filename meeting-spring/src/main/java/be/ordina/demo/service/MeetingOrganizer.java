package be.ordina.demo.service;

import be.ordina.demo.meeting.Meeting;
import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.Participant;
import be.ordina.demo.meeting.repo.MeetingRepository;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import be.ordina.demo.meeting.repo.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
public class MeetingOrganizer {

    private final MeetingRoomRepository meetingRoomRepository;

    private final MeetingRoomInitializer meetingRoomInitializer;

    private final ParticipantRepository participantRepository;

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingOrganizer(MeetingRoomRepository meetingRoomRepository, MeetingRoomInitializer meetingRoomInitializer, MeetingRepository meetingRepository, ParticipantRepository participantRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingRoomInitializer = meetingRoomInitializer;
        this.participantRepository = participantRepository;
        this.meetingRepository = meetingRepository;
    }

    @RequestMapping(value = "/meetingRooms")
    public List<MeetingRoom> getMeetingRooms() {
        if(!meetingRoomRepository.hasMeetingRooms()){
            meetingRoomInitializer.initializeMeetingRooms();
        }
        return meetingRoomRepository.getAllMeetingRooms();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Meeting createMeeting(Meeting meeting, List<Participant> participants) {
        meetingRoomRepository.create(meeting.getMeetingRoom());
        for (Participant participant : participants) {
            participantRepository.create(participant);
        }
        return meetingRepository.createMeeting(meeting, participants);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteMeetings() {
        meetingRepository.deleteAllMeetings();
    }
}
