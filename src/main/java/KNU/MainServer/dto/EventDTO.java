package KNU.MainServer.dto;

import KNU.MainServer.Type.EventType;
import KNU.MainServer.domain.Event;
import KNU.MainServer.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EventDTO {

    private Long event_time;
    private EventType eventType;
    private Long participantId;

    //    private Long item_name
    public static EventDTO from(Event event, Participant participant) {
        return EventDTO.builder()
                .event_time(event.getTimestamp())
                .eventType(event.getEventType())
                .participantId(participant.getParticipantId())
                .build();
    }
}
