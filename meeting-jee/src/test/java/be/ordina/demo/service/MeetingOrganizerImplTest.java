package be.ordina.demo.service;

import be.ordina.demo.meeting.*;
import be.ordina.demo.meeting.repo.MeetingRepository;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import be.ordina.demo.meeting.repo.ParticipantRepository;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeetingOrganizerImplTest {
    @Mock
    private MeetingRoomInitializer meetingRoomInitializer;
    @Mock
    private MeetingRoomRepository meetingRoomRepository;
    @Mock
    private MeetingRepository meetingRepository;
    @Mock
    private ParticipantRepository participantRepository;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private MeetingOrganizer meetingOrganizer = new MeetingOrganizerImpl();

    private MeetingRoom meetingRoom;
    private Meeting meeting;
    private Participant participant;

    @Before
    public void setupFixtures() throws CapacityReachedException {

        meetingRoom = MeetingRoomMother.europaWithCapacity10WithId();
        meeting = MeetingMother.integrationTestingBetween16And18On15Dec2014InRoom(meetingRoom);
        participant = ParticipantMother.getHomerSimpson();
        meeting.addParticipant(participant);
    }

    @Test
    public void getMeetingRooms_no_meeting_rooms_available_calls_init_on_meetingroom_initializer() throws Exception {

        when(meetingRoomRepository.hasMeetingRooms()).thenReturn(false);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingRooms);

        assertThat(meetingOrganizer.getMeetingRooms(), sameInstance(meetingRooms));
        verify(meetingRoomInitializer, times(1)).initializeMeetingRooms(entityManager);
    }

    @Test
    public void getMeetingRooms_meeting_rooms_available_no_call_to_meetingroom_initializer() throws Exception {

        when(meetingRoomRepository.hasMeetingRooms()).thenReturn(true);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingRooms);

        assertThat(meetingOrganizer.getMeetingRooms(), sameInstance(meetingRooms));
        verify(meetingRoomInitializer, never()).initializeMeetingRooms(entityManager);
    }

    @Test
    public void createMeeting_creates_the_meetingRoom_first_then_the_meeting_then_creates_employees_and_adds_them() {

        List<Participant> participants = Arrays.asList(participant);

        when(meetingRoomRepository.create(meetingRoom)).thenReturn(meetingRoom);
        when(participantRepository.create(participant)).thenReturn(participant);
        when(meetingRepository.create(meeting, participants)).thenReturn(meeting);

        Meeting createdMeeting = meetingOrganizer.createMeeting(meeting, participants);

        assertThat(createdMeeting, sameInstance(meeting));
        verify(meetingRoomRepository, times(1)).create(meetingRoom);
        verify(participantRepository, times(1)).create(participant);
        verify(meetingRepository, times(1)).create(meeting, participants);

    }
}