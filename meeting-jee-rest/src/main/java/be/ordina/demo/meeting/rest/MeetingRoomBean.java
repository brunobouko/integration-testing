package be.ordina.demo.meeting.rest;

import java.util.Objects;
@SuppressWarnings("unused")//getters and setters needed to match bean properties for json marshalling
public class MeetingRoomBean {
    private String id;
    private String name;
    private Integer capacity;

    public MeetingRoomBean(String id, String name, Integer capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeetingRoomBean that = (MeetingRoomBean) o;

        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity);
    }
}
