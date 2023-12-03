package KNU.MainServer.repository;

import KNU.MainServer.domain.GameAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
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

        GameAccount result;
        try {
            result = query.getSingleResult();
            log.info("Repository : Results " + result.toString());
        } catch (NoResultException e) {
            log.error("No game account found with name: " + name);
            return null;
        } catch (NonUniqueResultException e) {
            log.error("Multiple game accounts found with name: " + name);
            return null;
        }

        return result;
    }

    public List<Object[]> findMatchAndParticipantInfoByAccountName
            (String name) {
        String sql = "SELECT ga, p, m, c FROM GameAccount ga "
                + "JOIN Participant p ON  ga.uniqueGameAccountId = p.gameAccount.id "
                + "JOIN Champion c ON p.champion.uniqueChampId = c.uniqueChampId "
                + "JOIN Team t ON t.teamId = p.team.teamId "
                + "JOIN Match m ON m.uniqueMatchId = t.match.id WHERE ga.gameName = :name";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Object[]> findParticipantDetailByMatchId
            (String matchId) {
        String sql = "SELECT ga, p, c  FROM Match m "
                + "JOIN Team t ON t.match.uniqueMatchId  = m.uniqueMatchId "
                + "JOIN Participant p ON t.teamId = p.team.teamId "
                + "JOIN Champion c ON c.uniqueChampId = p.champion.uniqueChampId "
                + "JOIN GameAccount ga ON ga.uniqueGameAccountId = p.gameAccount.uniqueGameAccountId "
                + "WHERE m.uniqueMatchId = :matchId";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("matchId", matchId);
        return query.getResultList();
    }

    public List<Object[]> findEventDetailByMatchId
            (String matchId) {
        String sql = "SELECT e, p, i  FROM Match m "
                + "JOIN Event e ON e.match.uniqueMatchId  = m.uniqueMatchId "
                + "JOIN Participant p ON e.participant.participantId = p.participantId "
                + "LEFT JOIN Purchase pu ON pu.event.uniqueEventId = e.uniqueEventId "
                + "LEFT JOIN Item i ON pu.item.itemId = i.itemId "
                + "WHERE m.uniqueMatchId = :matchId";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("matchId", matchId);
        return query.getResultList();
    }
}
