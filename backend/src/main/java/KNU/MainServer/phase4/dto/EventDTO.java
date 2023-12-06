package KNU.MainServer.phase4.dto;

import KNU.MainServer.global.type.EventType;
import KNU.MainServer.global.domain.Event;
import KNU.MainServer.global.domain.Item;
import KNU.MainServer.global.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EventDTO {

    private Long eventTime;
    private EventType eventType;
    private Long participantId;
    private String itemName;

    //    private Long item_name
    public static EventDTO from(Event event, Participant participant, Item item) {
        return EventDTO.builder()
                .eventTime(event.getTimestamp())
                .eventType(event.getEventType())
                .participantId(participant.getParticipantId())
                .itemName(item == null ? null : item.getName())
                .build();
    }
}
