package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Participant;
import be.ordina.demo.meeting.Meeting;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MeetingRepository {
    Meeting create(Meeting meeting, List<Participant> participants);
}
