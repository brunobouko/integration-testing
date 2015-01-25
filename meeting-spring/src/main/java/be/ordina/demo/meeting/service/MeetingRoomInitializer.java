package be.ordina.demo.meeting.service;

import be.ordina.demo.meeting.MeetingRoom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class MeetingRoomInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    public void initializeMeetingRooms() {
        entityManager.persist(MeetingRoom.meetingRoom().roomName("Europa").capacity(10).build());
        entityManager.flush();
    }

}
