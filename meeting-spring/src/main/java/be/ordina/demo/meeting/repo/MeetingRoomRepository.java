package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MeetingRoom create(MeetingRoom meetingRoom) {
        entityManager.persist(meetingRoom);
        return meetingRoom;
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
