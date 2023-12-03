package KNU.MainServer.dto;

import KNU.MainServer.domain.Champion;
import KNU.MainServer.domain.GameAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ParticipantDTO {
    private Long participantId;
    private String summonerName;
    private String selectedChampion;
    private String selectedChampionImage;


    public static ParticipantDTO from(GameAccount gameAccount, Long participant, Champion champion) {
        return ParticipantDTO.builder()
                .participantId(participant)
                .summonerName(gameAccount.getGameName())
                .selectedChampion(champion.getChampName())
                .selectedChampionImage(champion.getChampPhoto())
                .build();
    }
}
