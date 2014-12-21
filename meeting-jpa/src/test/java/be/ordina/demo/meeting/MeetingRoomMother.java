package be.ordina.demo.meeting;

import static be.ordina.demo.meeting.MeetingRoom.meetingRoom;

public class MeetingRoomMother {
    public static MeetingRoom europaWithCapacity10WithId() {
        return meetingRoom().id(1L).roomName("Europa").capacity(10).build();
    }
    public static MeetingRoom europaWithCapacity10WithOutId() {
        return meetingRoom().roomName("Europa").capacity(10).build();
    }
}
