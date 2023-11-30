package KNU.MainServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PARTICIPANT")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    @ManyToOne
    @JoinColumn(name = "Game_account_id")
    private GameAccount gameAccount;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer participantLevel;
    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "championId")
    private Champion champion;
    // getters and setters
}
