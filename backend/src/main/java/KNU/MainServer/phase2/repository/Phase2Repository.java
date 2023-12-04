package KNU.MainServer.phase2.repository;

import KNU.MainServer.domain.GameAccount;
import KNU.MainServer.domain.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.text.html.parser.Entity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class Phase2Repository {
    private final EntityManager em;

    public List<Object[]> findQuery3Result(Integer kill){
        String sql = "SELECT e.match.uniqueMatchId, count(e.uniqueEventId) "
                + "FROM Participant p, Event e, Team t "
                + "WHERE  e.participant.participantId = p.participantId "
                + "and p.team.teamId = t.teamId "
                + "and e.eventType = 'ITEM_PURCHASED' "
                + "and p.kills > :kill and t.isWin = 1 "
                + "group by e.participant.participantId, e.match.uniqueMatchId "
                + "ORDER BY e.participant.participantId";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("kill", kill);

        return query.getResultList();
    }

    public List<Object[]> findQuery6Result(Long eventTime) {

        String sql = "SELECT distinct e.eventType, e.timestamp, m.uniqueMatchId "
                + "FROM Match m JOIN Event e "
                + "ON m.uniqueMatchId  = e.match.uniqueMatchId "
                + "WHERE e.uniqueEventId IN "
                + "( SELECT e2.uniqueEventId "
                + "FROM Event e2 "
                + "WHERE e2.timestamp BETWEEN :startTime AND :endTime)\n"
                + "ORDER BY e.eventType  ASC";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("startTime", eventTime - 1000);
        query.setParameter("endTime", eventTime + 1000);

        return query.getResultList();
    }

    public List<Object[]> findQuery7Result(String champName) {

        String sql = "SELECT KILL, DEATH, ASSIST , CHAMP "
                + "FROM (SELECT p.kills AS KILL, p.deaths AS DEATH, p.assists AS ASSIST, c.champName AS CHAMP "
                + "FROM Participant p JOIN Champion c ON p.champion.uniqueChampId = c.uniqueChampId "
                + "WHERE c.champName = :champName) "
                + "ORDER BY CHAMP";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("champName", champName);

        return query.getResultList();
    }

    public List<Object[]> findQuery10Result(String matchId) {
        String sql1 = "SELECT m FROM Match m WHERE m.uniqueMatchId = :matchId ";
        TypedQuery<Match> query1 = em.createQuery(sql1, Match.class);
        query1.setParameter("matchId", matchId);
        Match result = query1.getSingleResult();

        log.info("found Match : " + result);

        String sql = "SELECT m.uniqueMatchId, m.duration FROM Match m WHERE m.duration < :matchDuration "
                + "INTERSECT "
                + "SELECT m2.uniqueMatchId, m2.duration FROM Match m2 WHERE m2.uniqueMatchId LIKE :matchId";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("matchDuration", result.getDuration());
        query.setParameter("matchId", result.getUniqueMatchId().substring(0,9) + "%");

        return query.getResultList();
    }
}
