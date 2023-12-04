package KNU.MainServer.phase2.response;

import KNU.MainServer.phase2.dto.Query7DTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Query7Response {
    private List<Query7DTO> response;
}
