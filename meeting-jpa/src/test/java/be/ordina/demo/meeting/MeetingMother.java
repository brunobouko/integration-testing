package be.ordina.demo.meeting;

import java.time.LocalDateTime;
import java.time.Month;

import static be.ordina.demo.meeting.Meeting.meeting;

public class MeetingMother {
    public static Meeting integrationTestingBetween16And18On15Dec2014InRoom(MeetingRoom meetingRoom) {
        LocalDateTime BEGIN = LocalDateTime.of(2014, Month.DECEMBER, 15, 16, 0);
        LocalDateTime END = LocalDateTime.of(2014, Month.DECEMBER, 15, 18, 0);
        return meeting().id(1L)
                .subject("Integration testing, why?")
                .meetingRoom(meetingRoom)
                .beginsAt(BEGIN)
                .endsAt(END).build();
    }
}
