package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Participant;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParticipantRepositoryImpl implements ParticipantRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Participant create(Participant participant) {
        entityManager.persist(participant);
        return participant;
    }
}
