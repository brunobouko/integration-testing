package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Meeting;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class MeetingRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Inject
    public MeetingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Meeting createMeeting(Meeting meeting) {
        entityManager.persist(meeting);
        return meeting;
    }
}
