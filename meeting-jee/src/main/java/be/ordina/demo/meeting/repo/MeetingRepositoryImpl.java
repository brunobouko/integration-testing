package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Meeting;
import be.ordina.demo.meeting.Participant;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MeetingRepositoryImpl implements MeetingRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Meeting create(Meeting meeting, List<Participant> participants) {
        for (Participant participant : participants) {
            meeting.addParticipant(participant);
        }
        entityManager.persist(meeting);
        return meeting;
    }
}
