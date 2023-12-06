package KNU.MainServer.phase3.dto;

import KNU.MainServer.global.type.TierType;
import KNU.MainServer.global.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ItemDTO {
    private Long itemId;
    private String itemName;
    private String description;
    private Integer price;

    public static ItemDTO from(Item item) {
        return ItemDTO.builder()
                .itemId(item.getItemId())
                .itemName(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();
    }
}
