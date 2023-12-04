package KNU.MainServer.dto;

import KNU.MainServer.Type.TierType;
import KNU.MainServer.domain.GameAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
