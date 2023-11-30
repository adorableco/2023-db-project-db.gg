package KNU.MainServer.repository;

import KNU.MainServer.domain.GameAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository @Slf4j
@RequiredArgsConstructor
public class GameAccountRepository {
    private final EntityManager em;

    public List<GameAccount> getGameAccountByName
            (String name){
        String sql = "SELECT ga FROM GameAccount ga WHERE ga.gameName LIKE :gameName";
        TypedQuery<GameAccount> query = em.createQuery(sql, GameAccount.class);
        query.setParameter("gameName",name+"%");

        List<GameAccount> result = query.getResultList();
        log.info("Repository : Results " + result.toString());

        return result;
    }
}
