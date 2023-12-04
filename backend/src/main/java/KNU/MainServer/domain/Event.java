package KNU.MainServer.domain;

import KNU.MainServer.type.EventType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueEventId;

    private Long timestamp;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @ManyToOne
    @JoinColumn(name = "Participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "Match_id")
    private Match match;

    @Override
    public String toString() {
        return "Event{" +
                "uniqueEventId=" + uniqueEventId +
                ", timestamp=" + timestamp +
                ", eventType=" + eventType +
                '}';
    }
}
