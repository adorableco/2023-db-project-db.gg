package KNU.MainServer.phase3.dto;

import KNU.MainServer.global.domain.Champion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ChampionDTO {
    private Long uniqueChampId;
    private String champName;
    private String champPhoto;

    public static ChampionDTO from(Champion champion) {
        return ChampionDTO.builder()
                .uniqueChampId(champion.getUniqueChampId())
                .champName(champion.getChampName())
                .champPhoto(champion.getChampPhoto())
                .build();
    }
}

