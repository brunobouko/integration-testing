package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;

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
    @Inject
    private MeetingRoomInitializer meetingRoomInitializer;
    @Override
    public List<MeetingRoom> getMeetingRooms() {
        if(!meetingRoomRepository.hasMeetingRooms()){
            meetingRoomInitializer.initializeMeetingRooms(entityManager);
        }
        return meetingRoomRepository.getAllMeetingRooms();
    }
}
