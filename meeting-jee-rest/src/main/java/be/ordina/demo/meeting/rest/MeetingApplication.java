package be.ordina.demo.meeting.rest;

import com.google.common.collect.Sets;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@SuppressWarnings("unchecked")
@ApplicationPath("/meeting")
public class MeetingApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Sets.<Class<?>>newHashSet(MeetingOrganizerRestService.class);
    }
}
