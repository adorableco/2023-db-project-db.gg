package KNU.MainServer.phase4.dto;

import KNU.MainServer.global.domain.Champion;
import KNU.MainServer.global.domain.Match;
import KNU.MainServer.global.domain.Participant;
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
    private String selectedChampion;
    private String selectedChampionImage;

    public static MatchInfoDTO from
            (Match match, Participant participant, Champion champion){

        return MatchInfoDTO.builder()
                .matchId(match.getUniqueMatchId())
                .kills(participant.getKills())
                .deaths(participant.getDeaths())
                .assists(participant.getAssists())
                .selectedChampion(champion.getChampName())
                .selectedChampionImage(champion.getChampPhoto())
                .build();
    }


}
