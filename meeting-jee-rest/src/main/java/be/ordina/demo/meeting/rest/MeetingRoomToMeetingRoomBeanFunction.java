package be.ordina.demo.meeting.rest;

import be.ordina.demo.meeting.MeetingRoom;
import com.google.common.base.Function;

class MeetingRoomToMeetingRoomBeanFunction implements Function<MeetingRoom, MeetingRoomBean> {
    @Override
    public MeetingRoomBean apply(MeetingRoom meetingRoom) {
        if (meetingRoom == null) {
            return null;
        }
        return new MeetingRoomBean(meetingRoom.getId().toString(), meetingRoom.getRoomName(), meetingRoom.getCapacity());
    }
}
