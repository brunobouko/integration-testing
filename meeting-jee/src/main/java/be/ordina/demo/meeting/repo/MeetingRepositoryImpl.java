package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Meeting;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MeetingRepositoryImpl implements MeetingRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Meeting createMeeting(Meeting meeting) {
        entityManager.persist(meeting);
        return meeting;
    }
}
