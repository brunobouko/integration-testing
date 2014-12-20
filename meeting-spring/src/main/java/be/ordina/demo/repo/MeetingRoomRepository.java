package be.ordina.demo.repo;

import be.ordina.demo.meeting.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class MeetingRoomRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final CriteriaFactory criteriaFactory;
    @Autowired
    public MeetingRoomRepository(EntityManager entityManager, CriteriaFactory criteriaFactory) {
        this.entityManager = entityManager;
        this.criteriaFactory = criteriaFactory;
    }

    public List<MeetingRoom> getAllMeetingRooms() {
        CriteriaQuery<MeetingRoom> roomCriteriaQuery = criteriaFactory.createCriteriaQueryWithRootSelection(entityManager, MeetingRoom.class);
        TypedQuery<MeetingRoom> typedQuery = entityManager.createQuery(roomCriteriaQuery);
        return typedQuery.getResultList();
    }


}
