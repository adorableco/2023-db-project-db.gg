package KNU.MainServer.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class PurchaseId implements Serializable {

    @Column(name = "Event_id")
    private Long eventId;
    @Column(name = "Item_id")
    private Long itemId;
}
