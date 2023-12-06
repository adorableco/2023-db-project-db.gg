package KNU.MainServer.dto;

import KNU.MainServer.global.Type.TierType;
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
                .itemId(item.getId())
                .itemName(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();
    }
}
