package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoomMother;

import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class MeetingRoomInitializer {
    public void initializeMeetingRooms(EntityManager entityManager) {
        entityManager.persist(MeetingRoomMother.europaWithCapacity10WithOutId());
        entityManager.flush();
    }
}
