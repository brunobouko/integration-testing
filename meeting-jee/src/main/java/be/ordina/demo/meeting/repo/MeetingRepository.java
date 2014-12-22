package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Meeting;

import javax.ejb.Local;

@Local
public interface MeetingRepository {
    Meeting createMeeting(Meeting meeting);
}
