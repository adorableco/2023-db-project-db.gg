package KNU.MainServer.service;

import KNU.MainServer.Response.GameAccountResponse;
import KNU.MainServer.domain.GameAccount;
import KNU.MainServer.dto.GameAccountDTO;
import KNU.MainServer.repository.GameAccountRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameAccountService {
    private final GameAccountRepository gameAccountRepository;

    public GameAccountResponse findGameAccounts(String userName) {
        List<GameAccount> gameAccounts =
                gameAccountRepository.getGameAccountBySimilarName(userName);

        return new GameAccountResponse(
                gameAccounts.stream()
                        .map(GameAccountDTO::from)
                        .collect(Collectors.toUnmodifiableList()));
    }

    public Long findGameAccountIdByName(String gameName) {
        GameAccount gameAccount = gameAccountRepository
                .getGameAccountByName(gameName);
        gameAccountRepository.findMatchAndParticipantInfoByAccountName(gameName);
        return null;
    }
}
