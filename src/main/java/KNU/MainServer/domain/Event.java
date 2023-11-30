package KNU.MainServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueEventId;

    private Long timestamp;
    private String eventType;
    @ManyToOne
    @JoinColumn(name = "Participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "Match_id")
    private Match match;
}
