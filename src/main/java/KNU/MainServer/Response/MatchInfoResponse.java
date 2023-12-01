package KNU.MainServer.Response;

import KNU.MainServer.dto.GameAccountDTO;
import KNU.MainServer.dto.MatchInfoDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchInfoResponse {
    private GameAccountDTO gameAccount;
    private List<MatchInfoDTO> matchInfos;
}
