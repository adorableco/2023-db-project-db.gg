package KNU.MainServer.response;

import KNU.MainServer.dto.GameAccountDTO;
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
