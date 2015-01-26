package be.ordina.demo.meeting.service;

import be.ordina.demo.meeting.MeetingRoom;

import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class MeetingRoomInitializer {
    public void initializeMeetingRooms(EntityManager entityManager) {
        entityManager.persist(MeetingRoom.meetingRoom().roomName("Europa").capacity(10).build());
        entityManager.persist(MeetingRoom.meetingRoom().roomName("Antwerp").capacity(15).build());
        entityManager.persist(MeetingRoom.meetingRoom().roomName("Ghent").capacity(5).build());
        entityManager.persist(MeetingRoom.meetingRoom().roomName("Liege").capacity(6).build());
        entityManager.flush();
    }
}
