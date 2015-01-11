package be.ordina.demo.meeting;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Meeting implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String subject;
    @NotNull
    private Timestamp beginning;
    @NotNull
    private Timestamp ending;
    @OneToOne
    @NotNull
    private MeetingRoom meetingRoom;
    @OneToMany
    private Set<Participant> participants;

    public static Builder meeting() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void addParticipant(Participant participant) {
        int futureOccupation = participants.size() + 1;
        if (!meetingRoom.isCapacityLeft(futureOccupation) && !meetingRoom.isEqualToCapacity(futureOccupation)) {
            throw new CapacityReachedException();
        }
        participants.add(participant);
    }

    public Integer getCurrentParticipants() {
        return participants.size();
    }

    public LocalDateTime getBeginning() {
        return beginning.toLocalDateTime();
    }

    public LocalDateTime getEnding() {
        return ending.toLocalDateTime();
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meeting meeting = (Meeting) o;

        return !((id != null && meeting.id != null) && (!id.equals(meeting.id)))
                && Objects.equals(id, meeting.id) && Objects.equals(subject, meeting.subject)
                && Objects.equals(beginning, meeting.beginning) && Objects.equals(ending, meeting.ending)
                && Objects.equals(meetingRoom.getId(), meeting.meetingRoom.getId());

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, beginning, ending, meetingRoom, participants);
    }

    public static class Builder {
        private Long id;
        private String subject;
        private LocalDateTime begin;
        private LocalDateTime end;
        private MeetingRoom meetingRoom;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder meetingRoom(MeetingRoom meetingRoom) {
            this.meetingRoom = meetingRoom;
            return this;
        }

        public Builder beginsAt(LocalDateTime begin) {
            this.begin = begin;
            return this;
        }

        public Builder endsAt(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public Meeting build(){
            Meeting meeting = new Meeting();
            meeting.id = id;
            meeting.subject = subject;
            meeting.meetingRoom = meetingRoom;
            meeting.beginning = Timestamp.valueOf(begin);
            meeting.ending = Timestamp.valueOf(end);
            meeting.participants = new HashSet<>();
            return meeting;
        }
    }
}
