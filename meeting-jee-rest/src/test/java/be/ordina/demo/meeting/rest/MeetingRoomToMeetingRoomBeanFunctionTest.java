package be.ordina.demo.meeting.rest;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.MeetingRoomMother;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class MeetingRoomToMeetingRoomBeanFunctionTest {
    private MeetingRoom meetingRoom = MeetingRoomMother.europaWithCapacity10WithId();
    private MeetingRoomBean meetingRoomBean = new MeetingRoomBean("1", "Europa", 10);
    @Test
    public void apply_should_transform_a_MeetingRoom_into_MeetingRoomBean() throws Exception {
        assertThat(new MeetingRoomToMeetingRoomBeanFunction().apply(meetingRoom), equalTo(meetingRoomBean));
    }

    @Test
    public void apply_with_null_should_return_null() throws Exception {
        assertThat(new MeetingRoomToMeetingRoomBeanFunction().apply(null), equalTo(null));
    }
}