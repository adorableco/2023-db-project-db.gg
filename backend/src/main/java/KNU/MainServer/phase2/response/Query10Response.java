package KNU.MainServer.phase2.response;

import KNU.MainServer.phase2.dto.Query10DTO;
import KNU.MainServer.phase2.dto.Query7DTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Query10Response {

    private List<Query10DTO> response;
}
