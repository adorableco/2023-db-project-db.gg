package KNU.MainServer.phase2.dto;

import KNU.MainServer.type.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Query6DTO {
    private EventType eventType;
    private Long eventTime;
    private String matchId;

    public static Query6DTO from
            (EventType eventType, Long eventTime, String matchId){
        return Query6DTO.builder()
                .eventType(eventType)
                .eventTime(eventTime)
                .matchId(matchId)
                .build();
    }
}
