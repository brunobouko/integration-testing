package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.repo.MeetingRoomRepository;
import com.google.common.collect.Lists;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetingOrganizerTest {
    @Mock
    private MeetingRoomRepository meetingRoomRepository;
    @Mock
    private MeetingRoom meetingRoom;
    @Test
    public void testGetMeetingRooms() throws Exception {
        MeetingOrganizer meetingOrganizer = new MeetingOrganizer(meetingRoomRepository);

        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingRooms);
        
        assertThat(meetingOrganizer.getMeetingRooms(), IsSame.sameInstance(meetingRooms));
    }
}