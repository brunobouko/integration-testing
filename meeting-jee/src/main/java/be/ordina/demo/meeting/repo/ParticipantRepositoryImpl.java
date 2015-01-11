package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Participant;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParticipantRepositoryImpl implements ParticipantRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Participant create(Participant participant) {
        entityManager.persist(participant);
        return participant;
    }
}
