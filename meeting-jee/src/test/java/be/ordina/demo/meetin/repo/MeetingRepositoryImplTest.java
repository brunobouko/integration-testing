package be.ordina.demo.meetin.repo;

import be.ordina.demo.meeting.Meeting;
import be.ordina.demo.meeting.repo.MeetingRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static be.ordina.demo.meeting.MeetingMother.integrationTestingBetween16And18On15Dec2014InRoom;
import static be.ordina.demo.meeting.MeetingRoomMother.europaWithCapacity10WithId;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRepositoryImplTest {

    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private MeetingRepositoryImpl meetingRepository;

    private Meeting meeting = integrationTestingBetween16And18On15Dec2014InRoom(europaWithCapacity10WithId());

    @Test
    public void testCreateMeeting() throws Exception {
        assertThat(meetingRepository.createMeeting(meeting), equalTo(meeting));
        verify(entityManager, times(1)).persist(meeting);
    }
}