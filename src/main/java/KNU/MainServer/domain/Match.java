package KNU.MainServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "MATCH")
public class Match {

    // getters and setters
    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    List<Team> teams;
    @Id
    private String uniqueMatchId;
    private Integer duration;
    private String matchType;

    @Override
    public String toString() {
        return "Match{" +
                "uniqueMatchId='" + uniqueMatchId + '\'' +
                ", duration=" + duration +
                ", matchType='" + matchType + '\'' +
                "}\n";
    }
}