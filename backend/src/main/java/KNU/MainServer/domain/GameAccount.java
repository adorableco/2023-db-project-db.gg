package KNU.MainServer.domain;


import KNU.MainServer.Type.TierType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@Table(name = "GAME_ACCOUNT")
public class GameAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueGameAccountId;
    private String gameName;
    private Integer accountLevel;
    @Enumerated(value = EnumType.STRING)
    private TierType tier;

    @OneToMany(mappedBy = "gameAccount", fetch = FetchType.LAZY)
    private List<Participant> participants;

    @Override
    public String toString() {
        return "GameAccount{" +
                "uniqueGameAccountId=" + uniqueGameAccountId +
                ", gameName='" + gameName + '\'' +
                ", accountLevel=" + accountLevel +
                ", tier=" + tier +
                "}\n";
    }
}