package KNU.MainServer.phase4.dto;

import KNU.MainServer.global.domain.Champion;
import KNU.MainServer.global.domain.GameAccount;
import KNU.MainServer.global.domain.Participant;
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


    public static ParticipantDTO from(GameAccount gameAccount, Participant participant, Champion champion) {
        return ParticipantDTO.builder()
                .participantId(participant.getParticipantId())
                .summonerName(gameAccount.getGameName())
                .selectedChampion(champion.getChampName())
                .selectedChampionImage(champion.getChampPhoto())
                .build();
    }
}
