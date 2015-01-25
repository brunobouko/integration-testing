package be.ordina.demo.meeting.service;

import be.ordina.demo.meeting.Meeting;
import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.Participant;
import be.ordina.demo.meeting.repo.MeetingRepository;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import be.ordina.demo.meeting.repo.ParticipantRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MeetingOrganizerImpl implements MeetingOrganizer {
    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    private MeetingRoomRepository meetingRoomRepository;
    @EJB
    private MeetingRepository meetingRepository;
    @EJB
    private ParticipantRepository participantRepository;
    @Inject
    private MeetingRoomInitializer meetingRoomInitializer;
    @Override
    public List<MeetingRoom> getMeetingRooms() {
        if(!meetingRoomRepository.hasMeetingRooms()){
            meetingRoomInitializer.initializeMeetingRooms(entityManager);
        }
        return meetingRoomRepository.getAllMeetingRooms();
    }

    @Override
    public Meeting createMeeting(Meeting meeting, List<Participant> participants) {
        meetingRoomRepository.create(meeting.getMeetingRoom());
        for (Participant participant : participants) {
            participantRepository.create(participant);
        }
        meetingRepository.create(meeting, participants);
        return meeting;
    }
}
