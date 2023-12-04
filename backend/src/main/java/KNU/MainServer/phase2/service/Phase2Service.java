package KNU.MainServer.phase2.service;

import KNU.MainServer.phase2.dto.Query3DTO;
import KNU.MainServer.phase2.repository.Phase2Repository;
import KNU.MainServer.phase2.response.Query3Response;
import java.util.List;
import java.util.stream.Collectors;
import javax.management.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Phase2Service {
    private final Phase2Repository phase2Repository;

    public Query3Response findQuery3Response(
            Integer kill){
        List<Object[]> query3Result = phase2Repository.findQuery3Result(kill);

        List<Query3DTO> response = query3Result.stream()
                .map(result -> Query3DTO.from((String) result[0],
                        (Long) result[1]))
                .collect(Collectors.toUnmodifiableList());

        return new Query3Response(response);
    }

}
