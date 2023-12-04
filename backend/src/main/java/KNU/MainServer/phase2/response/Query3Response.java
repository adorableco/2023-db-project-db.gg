package KNU.MainServer.phase2.response;

import KNU.MainServer.phase2.dto.Query3DTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Query3Response {
    List<Query3DTO> response;
}
