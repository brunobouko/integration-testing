package be.ordina.demo.meeting.rest;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.service.MeetingOrganizer;
import com.google.common.collect.Lists;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeetingOrganizerRestServiceTest {
    @InjectMocks
    private MeetingOrganizerRestService meetingOrganizerRestService;
    @Mock
    private MeetingOrganizer meetingOrganizer;
    @Mock
    private MeetingRoomToMeetingRoomBeanFunction meetingRoomToMeetingRoomBeanFunction;
    @Mock
    private MeetingRoom meetingRoom;
    @Mock
    private MeetingRoomBean meetingRoomBean;

    @Test
    public void aliveCheck_should_return_SimpleTextBean_with_alive_as_text() throws Exception {
        assertThat(meetingOrganizerRestService.aliveCheck(), IsEqual.equalTo(new SimpleTextBean("alive")));
    }

    @Test
    public void getMeetingRooms() throws Exception {
        when(meetingOrganizer.getMeetingRooms()).thenReturn(Lists.newArrayList(meetingRoom));
        when(meetingRoomToMeetingRoomBeanFunction.apply(meetingRoom)).thenReturn(meetingRoomBean);

        List<MeetingRoomBean> meetingRoomBeans = meetingOrganizerRestService.getMeetingRooms();

        assertThat(meetingRoomBeans, IsCollectionContaining.hasItems(meetingRoomBean));
        verify(meetingOrganizer).getMeetingRooms();
        verify(meetingRoomToMeetingRoomBeanFunction).apply(meetingRoom);
    }
}