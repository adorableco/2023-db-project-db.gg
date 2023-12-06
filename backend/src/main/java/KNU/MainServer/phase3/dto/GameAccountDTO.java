package KNU.MainServer.dto;

import KNU.MainServer.global.Type.TierType;
import KNU.MainServer.global.domain.GameAccount;
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
                .uniqueGameAccount(gameAccount.getUniqueGameAccount())
                .accountLevel(gameAccount.getAccountLevel())
                .tier(gameAccount.getTier())
                .gameName(gameAccount.getGameName())
                .build();
    }
}
