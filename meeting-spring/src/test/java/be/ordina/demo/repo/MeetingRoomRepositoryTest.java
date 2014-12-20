package be.ordina.demo.repo;

import be.ordina.demo.meeting.MeetingRoom;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRoomRepositoryTest {

    private MeetingRoomRepository meetingRoomRepository;
    @Mock
    private EntityManager entityManager;
    @Mock
    private CriteriaFactory criteriaFactory;
    @Mock
    private CriteriaQuery<MeetingRoom> criteriaQuery;
    @Mock
    private TypedQuery<MeetingRoom> typedQuery;
    @Mock
    private MeetingRoom meetingRoom;

    @Before
    public void setupMeetingRoomRepository() {
        meetingRoomRepository = new MeetingRoomRepository(entityManager, criteriaFactory);
    }

    @Test
    public void testGetAllMeetingRooms() throws Exception {

        when(criteriaFactory.createCriteriaQueryWithRootSelection(entityManager, MeetingRoom.class)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(typedQuery.getResultList()).thenReturn(meetingRooms);


        assertThat(meetingRoomRepository.getAllMeetingRooms(), sameInstance(meetingRooms));
        verify(typedQuery, atMost(1)).getResultList();

    }
}