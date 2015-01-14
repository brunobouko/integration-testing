package be.ordina.demo.integrationtests.spring;

import be.ordina.demo.Application;
import be.ordina.demo.meeting.*;
import be.ordina.demo.service.MeetingOrganizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static be.ordina.demo.meeting.MeetingMother.familyMeetingBetween16And18On15Dec2014InRoom;
import static be.ordina.demo.meeting.MeetingMother.integrationTestingBetween16And18On15Dec2014InRoomWithoutId;
import static be.ordina.demo.meeting.MeetingRoomMother.europaWithCapacity10WithOutId;
import static be.ordina.demo.meeting.MeetingRoomMother.moesWithCapacity4WithOutId;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class SpringIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MeetingOrganizer meetingOrganizer;

    @Test
    public void createMeeting_meeting_with_different_employees_within_capacity_range() {
        //given
        MeetingRoom meetingRoom = europaWithCapacity10WithOutId();
        Meeting meeting = integrationTestingBetween16And18On15Dec2014InRoomWithoutId(meetingRoom);
        Participant johDoe = ParticipantMother.getJohDoe();
        Participant homerSimpson = ParticipantMother.getHomerSimpson();

        List<Participant> participants = Arrays.asList(johDoe, homerSimpson);

        //when
        Meeting createdMeeting = meetingOrganizer.createMeeting(meeting, participants);

        //then
        assertThat_meeting_meetingRoom_and_participants_where_persisted(meetingRoom, meeting, participants, createdMeeting);
    }

    @SuppressWarnings("unchecked")
    private void assertThat_meeting_meetingRoom_and_participants_where_persisted(MeetingRoom meetingRoom, Meeting meeting, List<Participant> participants, Meeting createdMeeting) {
        assertThat(entityManager.find(MeetingRoom.class, meetingRoom.getId()), equalTo(meetingRoom));
        for (Participant participant : participants) {
            assertThat(entityManager.find(Participant.class, participant.getId()), equalTo(participant));
        }
        Meeting foundMeeting = entityManager.find(Meeting.class, meeting.getId());
        assertThat(createdMeeting, equalTo(foundMeeting));
        List<Participant> foundParticipants = entityManager.createQuery("select participant from Participant participant order by participant.lastName").getResultList();
        assertThat(Arrays.equals(participants.toArray(), foundParticipants.toArray()), equalTo(true));
    }

    @Test
    public void createMeeting_meeting_with_different_employees_over_capacity_of_meeting_room() {
        //given
        meetingOrganizer.deleteMeetings();
        MeetingRoom meetingRoom = moesWithCapacity4WithOutId();
        Meeting meeting = familyMeetingBetween16And18On15Dec2014InRoom(meetingRoom);
        List<Participant> simpsons = ParticipantMother.getTheSimpsons();

        //when
        try {
            meetingOrganizer.createMeeting(meeting, simpsons);
            fail("no exception was thrown");
        } catch (CapacityReachedException e) {
            //fine we continue
        }

        //then moe's tavern and every Simpson must 've been persisted, no meeting may have been created
        assertThat_meetingRoom_and_participants_where_persisted_but_not_the_meeting(meetingRoom, simpsons);
    }

    private void assertThat_meetingRoom_and_participants_where_persisted_but_not_the_meeting(MeetingRoom meetingRoom, List<Participant> simpsons) {
        assertThat(entityManager.find(MeetingRoom.class, meetingRoom.getId()), equalTo(meetingRoom));
        for (Participant simpson : simpsons) {
            assertThat(entityManager.find(Participant.class, simpson.getId()), equalTo(simpson));
        }
        assertThat(entityManager.createQuery("select meeting from Meeting meeting").getResultList().isEmpty(), equalTo(true));
    }


}
