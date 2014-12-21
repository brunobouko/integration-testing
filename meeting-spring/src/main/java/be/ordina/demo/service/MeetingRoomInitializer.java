package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoomMother;
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
        entityManager.persist(MeetingRoomMother.europaWithCapacity10WithOutId());
        entityManager.flush();
    }

}
