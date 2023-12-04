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
    private Integer duration;

    public static Query10DTO from
            (String matchId, Integer duration){
        return Query10DTO.builder()
                .matchId(matchId)
                .duration(duration)
                .build();
    }
}
