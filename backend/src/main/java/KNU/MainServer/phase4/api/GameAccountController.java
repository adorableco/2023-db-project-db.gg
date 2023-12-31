package KNU.MainServer.phase4.api;


import KNU.MainServer.phase4.response.GameAccountResponse;
import KNU.MainServer.phase4.response.MatchDetailResponse;
import KNU.MainServer.phase4.response.MatchInfoResponse;
import KNU.MainServer.phase4.service.GameAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/summoner")
public class GameAccountController {
    private final GameAccountService gameAccountService;

    @GetMapping
    public ResponseEntity<GameAccountResponse> getSummonerInfo(
            @RequestParam String summonerName) {

        log.info("getSummonerInfo input Param [userName] : " + summonerName);

        return ResponseEntity.ok(gameAccountService.findGameAccounts(summonerName));
    }

    @GetMapping("/match")
    public ResponseEntity<MatchInfoResponse> getMatchInfo(
            @RequestParam(name = "gameName") String gameName) {

        log.info("getMatchInfo input param [gameAccountId] : " + gameName);

        MatchInfoResponse response = gameAccountService.findGameAccountIdByName(gameName);

        log.info("Return Value for get Match Info: " + response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/match/log")
    public ResponseEntity<MatchDetailResponse> getMatchDetail(
            @RequestParam(name = "matchId") String matchId) {
        log.info("getMatchDetail request matchId : " + matchId);

        MatchDetailResponse response = gameAccountService.findMatchDetailByMatchId(matchId);

        return ResponseEntity.ok(response);
    }
}
