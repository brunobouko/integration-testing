package be.ordina.demo.meeting;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

import static be.ordina.demo.meeting.MeetingRoom.meetingRoom;

public class MeetingRoomMother {
    public static MeetingRoom europaWithCapacity10WithId() {
        return meetingRoom().id(1L).roomName("Europa").capacity(10).build();
    }
    public static MeetingRoom europaWithCapacity10WithOutId() {
        return meetingRoom().roomName("Europa").capacity(10).build();
    }

    public static MeetingRoom moesWithCapacity4WithOutId() {
        return meetingRoom().roomName("Moe's Tavern").capacity(4).build();
    }

    public static MeetingRoom parisWithCapacity20WithId() {
        return meetingRoom().id(2L).roomName("Paris").capacity(20).build();
    }

    public static MeetingRoom parisWithCapacity20WithoutId() {
        return meetingRoom().roomName("Paris").capacity(20).build();
    }

    public static MeetingRoom[] initialRooms() {
        return new MeetingRoom[] {
                MeetingRoom.meetingRoom().roomName("Europa").capacity(10).build(),
                MeetingRoom.meetingRoom().roomName("Antwerp").capacity(15).build(),
                MeetingRoom.meetingRoom().roomName("Ghent").capacity(5).build(),
                MeetingRoom.meetingRoom().roomName("Liege").capacity(6).build()
        };
    }
}
