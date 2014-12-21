package be.ordina.demo.meeting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MeetingRoomTest {

    private MeetingRoom meetingRoom;

    @Before
    public void setupMeetingRoom() {
        meetingRoom = MeetingRoomMother.europaWithCapacity10WithId();
    }

    @Test
    public void getId_returns_id() throws Exception {
        assertThat(meetingRoom.getId(), equalTo(1L));
    }

    @Test
    public void getRoomName_returns_roomName () throws Exception {
        assertThat(meetingRoom.getRoomName(), equalTo("Europa"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void isCapacityLeft_null_throws_UnsupportedOperationException() {
        meetingRoom.isCapacityLeft(null);
    }

    @Test
    public void isCapacityLeft_currentOccupancy_less_than_capacity_returns_true() {
        assertThat(meetingRoom.isCapacityLeft(1), equalTo(true));
    }

    @Test
    public void isCapacityLeft_currentOccupancy_greater_than_capacity_returns_false() {
        assertThat(meetingRoom.isCapacityLeft(11), equalTo(false));
    }

    @Test
    public void isCapacityLeft_currentOccupancy_equal_to_capacity_returns_false() {
        assertThat(meetingRoom.isCapacityLeft(10), equalTo(false));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void isEqualToCapacity_currentOccupancy_null_throws_UnsupportedOperationException() {
        meetingRoom.isEqualToCapacity(null);
    }

    @Test
    public void isEqualToCapacity_currentOccupancy_equal_to_capacity_returns_true() {
        assertThat(meetingRoom.isEqualToCapacity(10), equalTo(true));
    }

    @Test
    public void isEqualToCapacity_currentOccupancy_not_equal_to_capacity_returns_false() {
        assertThat(meetingRoom.isEqualToCapacity(5), equalTo(false));
    }

}