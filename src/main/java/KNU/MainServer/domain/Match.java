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

    @Id
    private String uniqueMatchId;
    private Integer duration;
    private String matchType;
    // getters and setters
    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    List<Team> teams;
}