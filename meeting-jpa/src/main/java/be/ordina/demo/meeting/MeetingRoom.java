package be.ordina.demo.meeting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
public class MeetingRoom implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String roomName;
    @NotNull
    private Integer capacity;

    public Long getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isCapacityLeft(Integer currentOccupation) {
        if(currentOccupation == null) {
            throw new UnsupportedOperationException("isCapacityLeft can not be called with null");
        }
        return currentOccupation < capacity;
    }

    public boolean isEqualToCapacity(Integer currentOccupation) {
        if (currentOccupation == null) {
            throw new UnsupportedOperationException("isEqualToCapacity can not be called with null");
        }
        return capacity.equals(currentOccupation);
    }

    public static Builder meetingRoom() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String roomName;
        private Integer capacity;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder roomName(String roomName) {
            this.roomName = roomName;
            return this;
        }

        public MeetingRoom build(){
            MeetingRoom meetingRoom = new MeetingRoom();
            meetingRoom.id = id;
            meetingRoom.roomName = roomName;
            meetingRoom.capacity = capacity;
            return meetingRoom;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }
    }
}
