package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import com.google.common.collect.Lists;
import org.hamcrest.core.IsSame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeetingOrganizerTest {
    @Mock
    private MeetingRoomRepository meetingRoomRepository;
    @Mock
    private MeetingRoomInitializer meetingRoomInitializer;
    @Mock
    private MeetingRoom meetingRoom;
    private MeetingOrganizer meetingOrganizer;

    @Before
    public void setupMeetingOrganizer() {
        meetingOrganizer = new MeetingOrganizer(meetingRoomRepository, meetingRoomInitializer);
    }

    @Test
    public void getMeetingRooms_no_meeting_rooms_available_calls_init_on_meetingroom_initializer() throws Exception {

        when(meetingRoomRepository.hasMeetingRooms()).thenReturn(false);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingRooms);

        assertThat(meetingOrganizer.getMeetingRooms(), IsSame.sameInstance(meetingRooms));
        verify(meetingRoomInitializer, times(1)).initializeMeetingRooms();
    }

    @Test
    public void getMeetingRooms_meeting_rooms_available_no_call_to_meetingroom_initializer() throws Exception {

        when(meetingRoomRepository.hasMeetingRooms()).thenReturn(true);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingRooms);

        assertThat(meetingOrganizer.getMeetingRooms(), IsSame.sameInstance(meetingRooms));
        verify(meetingRoomInitializer, never()).initializeMeetingRooms();
    }
}