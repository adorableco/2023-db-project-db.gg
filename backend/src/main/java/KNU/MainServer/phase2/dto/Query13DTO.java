package KNU.MainServer.phase2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Query13DTO {
    private Integer isWin;
    private Long teamId;
    private Integer duration;

    public static Query13DTO from
            (Integer isWin, Long teamId, Integer duration){
        return Query13DTO.builder()
                .isWin(isWin)
                .teamId(teamId)
                .duration(duration)
                .build();
    }
}
