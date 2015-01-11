package be.ordina.demo.service;

import be.ordina.demo.meeting.*;
import be.ordina.demo.meeting.repo.MeetingRepository;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import be.ordina.demo.meeting.repo.ParticipantRepository;
import com.google.common.collect.Lists;
import org.hamcrest.core.IsSame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeetingOrganizerTest {
    @Mock
    private MeetingRoomRepository meetingRoomRepository;
    @Mock
    private MeetingRoomInitializer meetingRoomInitializer;
    @Mock
    private MeetingRepository meetingRepository;
    @Mock
    private ParticipantRepository participantRepository;

    private Meeting meeting;
    private MeetingRoom meetingRoom;
    private Participant participant;

    private MeetingOrganizer meetingOrganizer;

    @Before
    public void setupFixtures() throws CapacityReachedException {

        meetingRoom = MeetingRoomMother.europaWithCapacity10WithId();
        meeting = MeetingMother.integrationTestingBetween16And18On15Dec2014InRoom(meetingRoom);
        participant = ParticipantMother.getHomerSimpson();
        meeting.addParticipant(participant);
    }

    @Before
    public void setupMeetingOrganizer() {
        meetingOrganizer = new MeetingOrganizer(meetingRoomRepository, meetingRoomInitializer, meetingRepository, participantRepository);
    }

    @Test
    public void getMeetingRooms_no_meeting_rooms_available_calls_init_on_meetingroom_initializer() throws Exception {

        when(meetingRoomRepository.hasMeetingRooms()).thenReturn(false);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingRooms);

        assertThat(meetingOrganizer.getMeetingRooms(), IsSame.sameInstance(meetingRooms));
        verify(meetingRoomInitializer, times(1)).initializeMeetingRooms();
    }

    @Test
    public void getMeetingRooms_meeting_rooms_available_no_call_to_meetingroom_initializer() throws Exception {

        when(meetingRoomRepository.hasMeetingRooms()).thenReturn(true);
        ArrayList<MeetingRoom> meetingRooms = Lists.newArrayList(meetingRoom);
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingRooms);

        assertThat(meetingOrganizer.getMeetingRooms(), IsSame.sameInstance(meetingRooms));
        verify(meetingRoomInitializer, never()).initializeMeetingRooms();
    }

    @Test
    public void createMeeting_creates_the_meetingRoom_first_then_the_meeting_then_creates_employees_and_adds_them() {

        List<Participant> participants = Arrays.asList(participant);

        when(meetingRoomRepository.create(meetingRoom)).thenReturn(meetingRoom);
        when(participantRepository.create(participant)).thenReturn(participant);
        when(meetingRepository.createMeeting(meeting, participants)).thenReturn(meeting);

        Meeting createdMeeting = meetingOrganizer.createMeeting(meeting, participants);

        assertThat(createdMeeting, sameInstance(meeting));
        verify(meetingRoomRepository, times(1)).create(meetingRoom);
        verify(participantRepository, times(1)).create(participant);
        verify(meetingRepository, times(1)).createMeeting(meeting, participants);

    }
}