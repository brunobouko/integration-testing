package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.MeetingRoom;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Named
public class MeetingRoomRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final CriteriaFactory criteriaFactory;
    @Inject
    public MeetingRoomRepository(EntityManager entityManager, CriteriaFactory criteriaFactory) {
        this.entityManager = entityManager;
        this.criteriaFactory = criteriaFactory;
    }

    public List<MeetingRoom> getAllMeetingRooms() {
        CriteriaQuery<MeetingRoom> roomCriteriaQuery = criteriaFactory.createCriteriaQueryWithRootSelection(entityManager, MeetingRoom.class);
        TypedQuery<MeetingRoom> typedQuery = entityManager.createQuery(roomCriteriaQuery);
        return typedQuery.getResultList();
    }


    public boolean hasMeetingRooms() {
        CriteriaQuery<Long> countCriteriaQuery = criteriaFactory.createCountQueryWithRootSelection(entityManager, MeetingRoom.class);
        TypedQuery<Long> typedQuery = entityManager.createQuery(countCriteriaQuery);
        return typedQuery.getSingleResult() > 0;
    }
}
