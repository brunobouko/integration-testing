package be.ordina.demo.meeting.rest;

import com.google.common.collect.Sets;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class MeetingApplicationTest {
    private MeetingApplication meetingApplication = new MeetingApplication();
    @SuppressWarnings("unchecked")
    @Test
    public void testGetClasses() throws Exception {
        assertThat(meetingApplication.getClasses(), IsEqual.<Set<Class<?>>>equalTo(Sets.<Class<?>>newHashSet(MeetingOrganizerRestService.class)));
    }
}