package be.ordina.demo.meeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static be.ordina.demo.meeting.EmployeeMother.getHomerSimpson;
import static be.ordina.demo.meeting.EmployeeMother.getJohDoe;
import static be.ordina.demo.meeting.Meeting.meeting;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetingTest {

    public static final LocalDateTime BEGIN = LocalDateTime.of(2014, Month.DECEMBER, 13, 18, 0);
    public static final LocalDateTime END = LocalDateTime.of(2014, Month.DECEMBER, 13, 19, 0);
    public static final String INTEGRATION_TESTING_WHY = "Integration testing, why?";
    private Meeting meeting;
    @Mock
    private MeetingRoom meetingRoom;

    @Before
    public void setupMeeting() {
        meeting = meeting().id(1L)
                .subject(INTEGRATION_TESTING_WHY)
                .meetingRoom(meetingRoom)
                .beginsAt(BEGIN)
                .endsAt(END).build();
    }

    @Before
    public void setupExpectancyForMeetingRoomCapacityLeftAlwaysTrue() {
        when(meetingRoom.isCapacityLeft(1)).thenReturn(true);
        when(meetingRoom.isCapacityLeft(2)).thenReturn(true);
    }

    @Test
    public void getId_returns_id() {
        assertThat(meeting.getId(), equalTo(1L));
    }

    @Test
    public void getSubject_returns_subject() {
        assertThat(meeting.getSubject(), equalTo(INTEGRATION_TESTING_WHY));
    }

    @Test
    public void getBegin_returns_begin() {
        assertThat(meeting.getBegin(), equalTo(BEGIN));
    }

    @Test
    public void getEnd_returns_end() {
        assertThat(meeting.getEnd(), equalTo(END));
    }

    @Test
    public void addEmployee_to_new_meeting_adds_an_employee() throws Exception {

        meeting.addEmployee(getJohDoe());

        assertThat(meeting.getCurrentParticipants(), equalTo(1));

        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(1);
    }

    @Test
    public void addEmployee_same_employee_twice_does_not_add_twice() throws Exception {

        meeting.addEmployee(getJohDoe());
        meeting.addEmployee(getJohDoe());

        assertThat(meeting.getCurrentParticipants(), equalTo(1));

        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(1);
        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(2);
    }

    @Test
    public void addEmployee_other_employee_adds_other_employee() throws Exception {

        meeting.addEmployee(getJohDoe());
        meeting.addEmployee(getHomerSimpson());

        assertThat(meeting.getCurrentParticipants(), equalTo(2));

        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(1);
        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(2);
    }

    @Test
    public void addEmployee_capacity_reached_and_equal_to_capacity_adds_employee() throws Exception {
        when(meetingRoom.isCapacityLeft(2)).thenReturn(false);
        when(meetingRoom.isEqualToCapacity(2)).thenReturn(true);

        meeting.addEmployee(getJohDoe());
        meeting.addEmployee(getHomerSimpson());

        assertThat(meeting.getCurrentParticipants(), equalTo(2));

        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(1);
        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(2);
        Mockito.verify(meetingRoom, Mockito.times(1)).isEqualToCapacity(2);
    }

    @Test(expected=CapacityReachedException.class)
    public void addEmployee_capacity_reached_and_not_equal_to_capacity_throws_CapacityReachedException() throws Exception {
        when(meetingRoom.isCapacityLeft(2)).thenReturn(false);
        when(meetingRoom.isEqualToCapacity(2)).thenReturn(false);

        meeting.addEmployee(getJohDoe());
        meeting.addEmployee(getHomerSimpson());


        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(1);
        Mockito.verify(meetingRoom, Mockito.times(1)).isCapacityLeft(2);
        Mockito.verify(meetingRoom, Mockito.times(1)).isEqualToCapacity(2);

    }

}