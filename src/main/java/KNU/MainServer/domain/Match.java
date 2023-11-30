package KNU.MainServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MATCH")
public class Match {
    @Id
    private String uniqueMatchId;
    private Integer duration;
    private String matchType;
    // getters and setters
}