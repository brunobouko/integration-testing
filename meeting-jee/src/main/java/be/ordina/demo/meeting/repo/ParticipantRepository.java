package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Participant;

import javax.ejb.Local;

@Local
public interface ParticipantRepository {
    Participant create(Participant participant);
}
