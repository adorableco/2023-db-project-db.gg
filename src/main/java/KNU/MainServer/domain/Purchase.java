package KNU.MainServer.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "PURCHASE")
public class Purchase {

    @EmbeddedId
    private PurchaseId purchaseId;
    @MapsId("itemId")
    @ManyToOne
    @JoinColumn(name = "Item_id")
    private Item item;
    @MapsId("eventId")
    @ManyToOne
    @JoinColumn(name = "Event_id")
    private Event event;
    private Integer qty;
    // getters and setters
}
