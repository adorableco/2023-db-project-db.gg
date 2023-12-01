package KNU.MainServer.repository;

import KNU.MainServer.domain.GameAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SelectQueryEntityManager {
    private final EntityManager em;

    public List<GameAccount> getGameAccountBySimilarName
            (String name) {
        String sql = "SELECT ga FROM GameAccount ga WHERE ga.gameName LIKE :gameName";
        TypedQuery<GameAccount> query = em.createQuery(sql, GameAccount.class);
        query.setParameter("gameName", name + "%");

        List<GameAccount> result = query.getResultList();
        log.info("Repository : Results " + result.toString());

        return result;
    }

    public GameAccount getGameAccountByName
            (String name) {
        String sql = "SELECT ga FROM GameAccount ga WHERE ga.gameName LIKE :gameName";
        TypedQuery<GameAccount> query = em.createQuery(sql, GameAccount.class);
        query.setParameter("gameName", name);

        GameAccount result = query.getSingleResult();
        log.info("Repository : Results " + result.toString());

        return result;
    }

    public List<Object[]> findMatchAndParticipantInfoByAccountName
            (String name) {
        String sql = "SELECT ga, p, m FROM GameAccount ga "
                + "JOIN Participant p ON  ga.uniqueGameAccountId = p.gameAccount.id "
                + "JOIN Team t ON t.teamId = p.team.teamId "
                + "JOIN Match m ON m.uniqueMatchId = t.match.id WHERE ga.gameName = :name";
        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("name", name);
        List<Object[]> results = query.getResultList();
        log.info("Query results: " + Arrays.toString(results.get(0)));
        return results;
    }
}
