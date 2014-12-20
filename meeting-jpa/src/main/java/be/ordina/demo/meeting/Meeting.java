package be.ordina.demo.meeting;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Meeting implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String subject;
    @NotNull
    private LocalDateTime begin;
    @NotNull
    private LocalDateTime end;
    @OneToOne
    @NotNull
    private MeetingRoom meetingRoom;
    @OneToMany
    private Set<Employee> employees;

    public static Builder meeting() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void addEmployee(Employee employee) throws CapacityReachedException {
        int futureOccupation = employees.size() + 1;
        if (!meetingRoom.isCapacityLeft(futureOccupation) && !meetingRoom.isEqualToCapacity(futureOccupation)) {
            throw new CapacityReachedException();
        }
        employees.add(employee);
    }

    public Integer getCurrentParticipants() {
        return employees.size();
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
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
            meeting.begin = begin;
            meeting.end = end;
            meeting.employees = new HashSet<>();
            return meeting;
        }
    }
}
