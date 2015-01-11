package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Meeting;
import be.ordina.demo.meeting.Participant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRepositoryTest {

    @Mock
    private EntityManager entityManager;

    private MeetingRepository meetingRepository;

    @Mock
    private Participant participant;
    @Mock
    private Meeting meeting;

    @Before
    public void setupMeetingRepository() {
        meetingRepository = new MeetingRepository(entityManager);
    }

    @Test
    public void testCreateMeeting() throws Exception {
        assertThat(meetingRepository.createMeeting(meeting, Arrays.asList(participant)), equalTo(meeting));
        verify(entityManager, times(1)).persist(meeting);
        verify(meeting, times(1)).addParticipant(participant);
    }
}