package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Meeting;
import be.ordina.demo.meeting.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MeetingRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public MeetingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Meeting createMeeting(Meeting meeting, List<Participant> participants) {
        for (Participant participant : participants) {
            meeting.addParticipant(participant);
        }
        entityManager.persist(meeting);
        return meeting;
    }
}
