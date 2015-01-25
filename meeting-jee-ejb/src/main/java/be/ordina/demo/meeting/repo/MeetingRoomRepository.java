package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.MeetingRoom;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MeetingRoomRepository {
    boolean hasMeetingRooms();

    List<MeetingRoom> getAllMeetingRooms();

    MeetingRoom create(MeetingRoom meetingRoom);
}
