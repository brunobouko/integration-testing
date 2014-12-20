package be.ordina.demo.repo;

import be.ordina.demo.meeting.Meeting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static be.ordina.demo.meeting.MeetingMother.integrationTestingBetween16And18On15Dec2014InRoom;
import static be.ordina.demo.meeting.MeetingRoomMother.europaWithCapacity10;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRepositoryTest {

    @Mock
    private EntityManager entityManager;

    private MeetingRepository meetingRepository;

    private Meeting meeting = integrationTestingBetween16And18On15Dec2014InRoom(europaWithCapacity10());

    @Before
    public void setupMeetingRepository() {
        meetingRepository = new MeetingRepository(entityManager);
    }

    @Test
    public void testCreateMeeting() throws Exception {
        assertThat(meetingRepository.createMeeting(meeting), equalTo(meeting));
        verify(entityManager, times(1)).persist(meeting);
    }
}