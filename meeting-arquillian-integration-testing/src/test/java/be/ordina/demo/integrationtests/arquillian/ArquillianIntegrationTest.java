package be.ordina.demo.integrationtests.arquillian;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.MeetingRoomMother;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import be.ordina.demo.service.MeetingOrganizer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

@RunWith(Arquillian.class)
public class ArquillianIntegrationTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MeetingRoom.class.getPackage())
                .addPackage(MeetingOrganizer.class.getPackage())
                .addPackage(MeetingRoomRepository.class.getPackage())
                .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private MeetingOrganizer meetingOrganizer;

    @Test
    public void testGetAllMeetingRooms() {
        assertThat(meetingOrganizer.getMeetingRooms(), hasItems(MeetingRoomMother.europaWithCapacity10WithId()));
    }

}
