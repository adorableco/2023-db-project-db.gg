package KNU.MainServer.phase4.response;

import KNU.MainServer.phase4.dto.GameAccountDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GameAccountResponse {

    private List<GameAccountDTO> gameAccounts;
}
