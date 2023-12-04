package KNU.MainServer.phase2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Query10DTO {
    private String matchId;
    private Long duration;

    public static Query10DTO from
            (String matchId, Long duration){
        return Query10DTO.builder()
                .matchId(matchId)
                .duration(duration)
                .build();
    }
}
