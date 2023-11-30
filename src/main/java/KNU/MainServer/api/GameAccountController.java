package KNU.MainServer.api;


import KNU.MainServer.Response.GameAccountResponse;
import KNU.MainServer.service.GameAccountService;
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
            @RequestParam String summonerName){

        log.info("getMatchInfo input Param [userName] : " + summonerName);

        return ResponseEntity.ok(gameAccountService.findGameAccounts(summonerName));
    }

}
