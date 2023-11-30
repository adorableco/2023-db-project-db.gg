package KNU.MainServer.dto;

import KNU.MainServer.domain.GameAccount;
import KNU.MainServer.domain.tierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameAccountDTO {

    private Long gameAccountId;
    private String gameName;
    private tierType tier;
    private Integer accountLevel;

    public static GameAccountDTO from(GameAccount gameAccount){
        return GameAccountDTO.builder()
                .gameAccountId(gameAccount.getUniqueGameAccountId())
                .accountLevel(gameAccount.getAccountLevel())
                .tier(gameAccount.getTier())
                .gameName(gameAccount.getGameName())
                .build();
    }
}
