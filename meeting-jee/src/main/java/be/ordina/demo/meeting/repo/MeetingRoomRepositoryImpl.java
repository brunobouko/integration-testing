package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.MeetingRoom;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class MeetingRoomRepositoryImpl implements MeetingRoomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private CriteriaFactory criteriaFactory;

    @Override
    public List<MeetingRoom> getAllMeetingRooms() {
        CriteriaQuery<MeetingRoom> roomCriteriaQuery = criteriaFactory.createCriteriaQueryWithRootSelection(entityManager, MeetingRoom.class);
        TypedQuery<MeetingRoom> typedQuery = entityManager.createQuery(roomCriteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public MeetingRoom create(MeetingRoom meetingRoom) {
        entityManager.persist(meetingRoom);
        return meetingRoom;
    }

    @Override
    public boolean hasMeetingRooms() {
        CriteriaQuery<Long> countCriteriaQuery = criteriaFactory.createCountQueryWithRootSelection(entityManager, MeetingRoom.class);
        TypedQuery<Long> typedQuery = entityManager.createQuery(countCriteriaQuery);
        return typedQuery.getSingleResult() > 0;
    }
}
