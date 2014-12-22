package be.ordina.demo.service;

import be.ordina.demo.meeting.MeetingRoom;

import javax.ejb.Local;
import java.util.List;
@Local
public interface MeetingOrganizer {
    List<MeetingRoom> getMeetingRooms();
}
