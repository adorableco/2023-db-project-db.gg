package KNU.MainServer.phase3.dto;

import KNU.MainServer.global.domain.GameAccount;
import KNU.MainServer.global.type.TierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameAccountDTO {

    private String gameName;
    private TierType tier;
    private Integer accountLevel;

    public static GameAccountDTO from(GameAccount gameAccount) {
        return GameAccountDTO.builder()
                .accountLevel(gameAccount.getAccountLevel())
                .tier(gameAccount.getTier())
                .gameName(gameAccount.getGameName())
                .build();
    }
}
