package KNU.MainServer.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GAME_ACCOUNT")
public class GameAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueGameAccountId;
    private String gameName;
    private Integer accountLevel;
    @Enumerated(value = EnumType.STRING)
    private tierType tier;
}