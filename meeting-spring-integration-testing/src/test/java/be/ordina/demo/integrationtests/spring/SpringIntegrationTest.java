package be.ordina.demo.integrationtests.spring;

import be.ordina.demo.Application;
import be.ordina.demo.meeting.MeetingRoomMother;
import be.ordina.demo.service.MeetingOrganizer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
public class SpringIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MeetingOrganizer meetingOrganizer;

    @Before
    public void setupMeetingRooms() throws Exception {
        entityManager.persist(MeetingRoomMother.europaWithCapacity10WithOutId());
    }

    @Test
    public void testGetAllMeetingRooms() {
        assertThat(meetingOrganizer.getMeetingRooms(), hasItems(MeetingRoomMother.europaWithCapacity10WithId()));
    }

}
