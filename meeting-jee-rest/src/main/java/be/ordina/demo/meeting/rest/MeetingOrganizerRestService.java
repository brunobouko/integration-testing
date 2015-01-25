package be.ordina.demo.meeting.rest;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.service.MeetingOrganizer;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
@Path("organizer")
public class MeetingOrganizerRestService {
    @EJB
    private MeetingOrganizer meetingOrganizer;

    @GET
    @Path("alive")
    @Produces("application/json")
    public SimpleTextBean aliveCheck() {
        return new SimpleTextBean("alive");
    }

    @GET
    @Path("meetingrooms")
    @Produces("application/json")
    public List<MeetingRoomBean> getMeetingRooms() {
        return FluentIterable.from(meetingOrganizer.getMeetingRooms()).transform(new Function<MeetingRoom, MeetingRoomBean>() {
            @Override
            public MeetingRoomBean apply(MeetingRoom meetingRoom) {
                return new MeetingRoomBean(meetingRoom.getId().toString(), meetingRoom.getRoomName(), meetingRoom.getCapacity());
            }
        }).toList();
    }

}
