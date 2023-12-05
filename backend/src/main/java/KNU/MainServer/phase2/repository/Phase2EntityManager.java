package KNU.MainServer.phase2.repository;

import KNU.MainServer.global.domain.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class Phase2EntityManager {
    private final EntityManager em;

    @Transactional(isolation = Isolation.READ_COMMITTED)
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

    @Transactional(isolation = Isolation.READ_COMMITTED)
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

    @Transactional(isolation = Isolation.READ_COMMITTED)
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

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Object[]> findQuery10Result(String matchId) {
        String sql1 = "SELECT m FROM Match m WHERE m.uniqueMatchId = :matchId ";
        TypedQuery<Match> query1 = em.createQuery(sql1, Match.class);
        query1.setParameter("matchId", matchId);
        Match result = query1.getSingleResult();

        log.info("found Match : " + result);

        String sql = "SELECT m.uniqueMatchId, m.duration FROM Match m "
                + "INTERSECT "
                + "SELECT m2.uniqueMatchId, m2.duration FROM Match m2 WHERE m2.uniqueMatchId LIKE :matchId";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("matchId", result.getUniqueMatchId().substring(0,7) + "%");

        return query.getResultList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Object[]> findQuery13Result(String matchId) {
        String sql1 = "SELECT m "
                + "FROM Match m "
                + "WHERE m.uniqueMatchId = :matchId ";

        TypedQuery<Match> query1 = em.createQuery(sql1, Match.class);
        query1.setParameter("matchId", matchId);
        Match match = query1.getSingleResult();


        String sql = "SELECT t.isWin , t.teamId , t.match.duration "
                + "FROM Team t "
                + "WHERE t.teamId  IN ( SELECT t2.teamId "
                + "FROM Team t2 JOIN Match m ON t2.match.uniqueMatchId = m.uniqueMatchId "
                + "WHERE m.duration BETWEEN :startTime AND :endTime ) "
                + "ORDER BY t.teamId  ASC ";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        query.setParameter("startTime", match.getDuration() - 30 );
        query.setParameter("endTime", match.getDuration() + 30 );

        return query.getResultList();
    }
}
