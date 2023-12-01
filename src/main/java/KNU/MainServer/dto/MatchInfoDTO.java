package KNU.MainServer.dto;

import KNU.MainServer.domain.Champion;
import KNU.MainServer.domain.Match;
import KNU.MainServer.domain.Participant;
import jakarta.servlet.http.Part;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
public class MatchInfoDTO {

    private String matchId;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private String selected_champion;
    private String selected_champion_image;

    public static MatchInfoDTO from
            (Match match, Participant participant, Champion champion){

        return MatchInfoDTO.builder()
                .matchId(match.getUniqueMatchId())
                .kills(participant.getKills())
                .deaths(participant.getDeaths())
                .assists(participant.getAssists())
                .selected_champion(champion.getChampName())
                .selected_champion_image(champion.getChampPhoto())
                .build();
    }


}
