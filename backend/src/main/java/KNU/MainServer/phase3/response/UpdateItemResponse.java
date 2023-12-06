package KNU.MainServer.phase3.response;

import KNU.MainServer.phase3.dto.ChampionDTO;
import KNU.MainServer.phase3.dto.ItemDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateItemResponse {
    private ItemDTO response;
}
