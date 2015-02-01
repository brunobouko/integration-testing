package be.ordina.demo.meeting.rest;

import be.ordina.demo.meeting.service.MeetingOrganizer;
import com.google.common.collect.FluentIterable;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
@Path("organizer")
public class MeetingOrganizerRestService implements RestService {
    @EJB
    private MeetingOrganizer meetingOrganizer;
    @Inject
    private MeetingRoomToMeetingRoomBeanFunction meetingRoomToMeetingRoomBeanFunction;

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
        return FluentIterable.from(meetingOrganizer.getMeetingRooms()).transform(meetingRoomToMeetingRoomBeanFunction).toList();
    }

}
