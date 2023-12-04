package KNU.MainServer.phase2.service;

import KNU.MainServer.phase2.dto.Query3DTO;
import KNU.MainServer.phase2.dto.Query6DTO;
import KNU.MainServer.phase2.repository.Phase2Repository;
import KNU.MainServer.phase2.response.Query3Response;
import KNU.MainServer.phase2.response.Query6Response;
import KNU.MainServer.type.EventType;
import java.util.List;
import java.util.stream.Collectors;
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

    public Query6Response findQuery6Response(
            Long eventTime){
        List<Object[]> query6Result = phase2Repository.findQuery6Result(eventTime);
        List<Query6DTO> response = query6Result.stream()
                .map(result -> Query6DTO.from(
                        (EventType) result[0],
                        (Long) result[1],
                        (String) result[2]))
                .collect(Collectors.toUnmodifiableList());

        return new Query6Response(response);
    }

}
