package be.ordina.demo.repo;

import be.ordina.demo.meeting.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MeetingRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public MeetingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Meeting createMeeting(Meeting meeting) {
        entityManager.persist(meeting);
        return meeting;
    }
}
