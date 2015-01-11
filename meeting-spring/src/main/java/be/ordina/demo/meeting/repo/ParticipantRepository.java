package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ParticipantRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public ParticipantRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Participant create(Participant participant) {
        entityManager.persist(participant);
        return participant;
    }
}
