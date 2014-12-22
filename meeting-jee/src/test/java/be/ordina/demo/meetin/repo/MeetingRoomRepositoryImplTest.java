package be.ordina.demo.meetin.repo;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.repo.CriteriaFactory;
import be.ordina.demo.meeting.repo.MeetingRoomRepositoryImpl;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRoomRepositoryImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private CriteriaFactory criteriaFactory;
    @InjectMocks
    private MeetingRoomRepositoryImpl meetingRoomRepository;

    @Mock
    private CriteriaQuery<MeetingRoom> criteriaQuery;
    @Mock
    private CriteriaQuery<Long> countQuery;
    @Mock
    private TypedQuery<MeetingRoom> typedQuery;
    @Mock
    private TypedQuery<Long> typedCountQuery;
    @Mock
    private MeetingRoom meetingRoom;

    @Test
    public void testGetAllMeetingRooms() throws Exception {

        when(criteriaFactory.createCriteriaQueryWithRootSelection(entityManager, MeetingRoom.class)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(typedQuery.getResultList()).thenReturn(meetingRooms);


        assertThat(meetingRoomRepository.getAllMeetingRooms(), sameInstance(meetingRooms));
        verify(typedQuery, times(1)).getResultList();

    }

    @Test
    public void hasMeetingRooms_count_zero_returns_false()throws Exception {
        when(criteriaFactory.createCountQueryWithRootSelection(entityManager, MeetingRoom.class)).thenReturn(countQuery);
        when(entityManager.createQuery(countQuery)).thenReturn(typedCountQuery);
        when(typedCountQuery.getSingleResult()).thenReturn(0L);

        assertThat(meetingRoomRepository.hasMeetingRooms(), equalTo(false));
        verify(typedCountQuery, times(1)).getSingleResult();
    }

    @Test
    public void hasMeetingRooms_count_more_than_zero_returns_true()throws Exception {
        when(criteriaFactory.createCountQueryWithRootSelection(entityManager, MeetingRoom.class)).thenReturn(countQuery);
        when(entityManager.createQuery(countQuery)).thenReturn(typedCountQuery);
        when(typedCountQuery.getSingleResult()).thenReturn(1L);

        assertThat(meetingRoomRepository.hasMeetingRooms(), equalTo(true));
        verify(typedCountQuery, times(1)).getSingleResult();
    }
}