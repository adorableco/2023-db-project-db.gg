package KNU.MainServer.phase2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Query7DTO {
    private Integer kill;
    private Integer death;
    private Integer assist;
    private String champName;

    public static Query7DTO from
            (Integer kill, Integer death, Integer assist, String champName){
        return Query7DTO.builder()
                .kill(kill)
                .death(death)
                .assist(assist)
                .champName(champName)
                .build();
    }
}
