package KNU.MainServer.phase2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Query3DTO {
    private String matchId;
    private Long itemPurchasedCnt;

    public static Query3DTO from(String matchId, Long itemPurchasedCnt){
        return Query3DTO.builder()
                .itemPurchasedCnt(itemPurchasedCnt)
                .matchId(matchId)
                .build();
    }
}
