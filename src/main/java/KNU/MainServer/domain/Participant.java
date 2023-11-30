package KNU.MainServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.data.util.Lazy;

@Entity
@Table(name = "PARTICIPANT")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;

    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer participantLevel;

    @ManyToOne
    @JoinColumn(name = "Game_account_id")
    private GameAccount gameAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Team_id")
    private Team team;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Champion_id")
    private Champion champion;
    // getters and setters
}
