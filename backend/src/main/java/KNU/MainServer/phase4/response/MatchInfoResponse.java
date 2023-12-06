package KNU.MainServer.phase4.response;

import KNU.MainServer.phase4.dto.GameAccountDTO;
import KNU.MainServer.phase4.dto.MatchInfoDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchInfoResponse {
    private GameAccountDTO gameAccount;
    private List<MatchInfoDTO> matchInfo;
}
