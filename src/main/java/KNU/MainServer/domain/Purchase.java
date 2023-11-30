package KNU.MainServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PURCHASE")
public class Purchase {
    @Id
    private Long eventId;
    @ManyToOne
    @JoinColumn(name = "Item_id")
    private Item item;
    private Integer qty;
    // getters and setters
}
