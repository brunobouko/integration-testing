package be.ordina.demo.meeting;

import static be.ordina.demo.meeting.MeetingRoom.meetingRoom;

public class MeetingRoomMother {
    public static MeetingRoom europaWithCapacity10() {
        return meetingRoom().id(1L).roomName("Europa").capacity(10).build();
    }
}
