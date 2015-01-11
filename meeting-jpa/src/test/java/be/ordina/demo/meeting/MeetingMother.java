package be.ordina.demo.meeting;

import java.time.LocalDateTime;
import java.time.Month;

import static be.ordina.demo.meeting.Meeting.meeting;

public class MeetingMother {

    public static final LocalDateTime FOUR_PM_15_DECEMBER_2014 = LocalDateTime.of(2014, Month.DECEMBER, 15, 16, 0);
    public static final LocalDateTime SIX_PM_15_DECEMBER_2014 = LocalDateTime.of(2014, Month.DECEMBER, 15, 18, 0);

    public static Meeting integrationTestingBetween16And18On15Dec2014InRoom(MeetingRoom meetingRoom) {
        return meeting().id(1L)
                .subject("Integration testing, why?")
                .meetingRoom(meetingRoom)
                .beginsAt(FOUR_PM_15_DECEMBER_2014)
                .endsAt(SIX_PM_15_DECEMBER_2014).build();
    }

    public static Meeting integrationTestingBetween16And18On15Dec2014InRoomWithoutId(MeetingRoom meetingRoom) {
        LocalDateTime END = SIX_PM_15_DECEMBER_2014;
        return meeting()
                .subject("Integration testing, why?")
                .meetingRoom(meetingRoom)
                .beginsAt(FOUR_PM_15_DECEMBER_2014)
                .endsAt(END).build();
    }

    public static Meeting familyMeetingBetween16And18On15Dec2014InRoom(MeetingRoom meetingRoom) {
        LocalDateTime END = SIX_PM_15_DECEMBER_2014;
        return meeting()
                .subject("Family Meeting, Urgent!")
                .meetingRoom(meetingRoom)
                .beginsAt(FOUR_PM_15_DECEMBER_2014)
                .endsAt(END).build();
    }
}
