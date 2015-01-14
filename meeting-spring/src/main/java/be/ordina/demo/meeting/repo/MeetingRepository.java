package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.CapacityReachedException;
import be.ordina.demo.meeting.Meeting;
import be.ordina.demo.meeting.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = CapacityReachedException.class)
    public Meeting createMeeting(Meeting meeting, List<Participant> participants) {
        entityManager.persist(meeting);
        for (Participant participant : participants) {
            meeting.addParticipant(participant);
        }
        return meeting;
    }

    public void deleteAllMeetings() {
        entityManager.createQuery("delete from Meeting").executeUpdate();
    }
}
