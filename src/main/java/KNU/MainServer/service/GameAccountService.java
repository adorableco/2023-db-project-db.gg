package KNU.MainServer.service;

import KNU.MainServer.domain.Champion;
import KNU.MainServer.domain.Event;
import KNU.MainServer.domain.GameAccount;
import KNU.MainServer.domain.Match;
import KNU.MainServer.domain.Participant;
import KNU.MainServer.dto.EventDTO;
import KNU.MainServer.dto.GameAccountDTO;
import KNU.MainServer.dto.MatchInfoDTO;
import KNU.MainServer.dto.ParticipantDTO;
import KNU.MainServer.repository.SelectQueryEntityManager;
import KNU.MainServer.response.GameAccountResponse;
import KNU.MainServer.response.MatchDetailResponse;
import KNU.MainServer.response.MatchInfoResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameAccountService {
    private final SelectQueryEntityManager selectQueryEntityManager;

    public GameAccountResponse findGameAccounts(String userName) {
        List<GameAccount> gameAccounts =
                selectQueryEntityManager.getGameAccountBySimilarName(userName);

        return new GameAccountResponse(
                gameAccounts.stream()
                        .map(GameAccountDTO::from)
                        .collect(Collectors.toUnmodifiableList()));
    }

    public MatchInfoResponse findGameAccountIdByName(String gameName) {
        GameAccount gameAccount = selectQueryEntityManager
                .getGameAccountByName(gameName);
        if (gameAccount == null) {
            return null;
        }
        List<Object[]> queryReturns =
                selectQueryEntityManager.findMatchAndParticipantInfoByAccountName(gameName);
        GameAccountDTO gameAccountDTO = GameAccountDTO.from((GameAccount) queryReturns.get(0)[0]);
        List<MatchInfoDTO> matchInfos = queryReturns.stream()
                .map(result -> MatchInfoDTO.from((Match) result[2],
                        (Participant) result[1],
                        (Champion) result[3]))
                .collect(Collectors.toUnmodifiableList());

        return new MatchInfoResponse(gameAccountDTO, matchInfos);
    }


    public MatchDetailResponse findMatchDetailByMatchId(String matchId) {
        List<ParticipantDTO> participants = findParticipantDetailByMatchId(matchId);
        List<EventDTO> events = findEventDetailByMatchId(matchId);
        return new MatchDetailResponse(participants, events);
    }

    private List<ParticipantDTO> findParticipantDetailByMatchId(String matchId) {
        List<Object[]> queryReturns =
                selectQueryEntityManager.findParticipantDetailByMatchId(matchId);
        log.info("findParticipantDetailByMatchId : " + queryReturns.get(0)[2]);
        return queryReturns.stream()
                .map(result -> ParticipantDTO.from((GameAccount) result[0],
                        (Participant) result[1],
                        (Champion) result[2]))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<EventDTO> findEventDetailByMatchId(String matchId) {
        List<Object[]> queryReturns =
                selectQueryEntityManager.findEventDetailByMatchId(matchId);
        return queryReturns.stream()
                .map(result -> EventDTO.from((Event) result[0],
                        (Participant) result[1]))
                .collect(Collectors.toUnmodifiableList());
    }
}
