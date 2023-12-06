package KNU.MainServer.phase3.response;

import KNU.MainServer.phase3.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InsertItemResponse {
    private ItemDTO response;
}
