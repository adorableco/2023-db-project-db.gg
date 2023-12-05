package KNU.MainServer.phase2.service;

import KNU.MainServer.phase2.dto.Query10DTO;
import KNU.MainServer.phase2.dto.Query13DTO;
import KNU.MainServer.phase2.dto.Query3DTO;
import KNU.MainServer.phase2.dto.Query6DTO;
import KNU.MainServer.phase2.dto.Query7DTO;
import KNU.MainServer.phase2.repository.Phase2EntityManager;
import KNU.MainServer.phase2.response.Query10Response;
import KNU.MainServer.phase2.response.Query13Response;
import KNU.MainServer.phase2.response.Query3Response;
import KNU.MainServer.phase2.response.Query6Response;
import KNU.MainServer.phase2.response.Query7Response;
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
    private final Phase2EntityManager phase2EntityManager;

    public Query3Response findQuery3Response(
            Integer kill){
        List<Object[]> query3Result = phase2EntityManager.findQuery3Result(kill);

        List<Query3DTO> response = query3Result.stream()
                .map(result -> Query3DTO.from((String) result[0],
                        (Long) result[1]))
                .collect(Collectors.toUnmodifiableList());

        return new Query3Response(response);
    }

    public Query6Response findQuery6Response(
            Long eventTime){
        List<Object[]> query6Result = phase2EntityManager.findQuery6Result(eventTime);
        List<Query6DTO> response = query6Result.stream()
                .map(result -> Query6DTO.from(
                        (EventType) result[0],
                        (Long) result[1],
                        (String) result[2]))
                .collect(Collectors.toUnmodifiableList());

        return new Query6Response(response);
    }

    public Query7Response findQuery7Response(
            String champName){
        List<Object[]> query7Result = phase2EntityManager.findQuery7Result(champName);
        List<Query7DTO> response = query7Result.stream()
                .map(result -> Query7DTO.from(
                        (Integer) result[0],
                        (Integer) result[1],
                        (Integer) result[2],
                        (String) result[3]))
                .collect(Collectors.toUnmodifiableList());

        return new Query7Response(response);
    }

    public Query10Response findQuery10Response(
            String matchId){
        List<Object[]> query10Result = phase2EntityManager.findQuery10Result(matchId);
        List<Query10DTO> response = query10Result.stream()
                .map(result -> Query10DTO.from(
                        (String) result[0],
                        (Integer) result[1]))
                .collect(Collectors.toUnmodifiableList());

        return new Query10Response(response);
    }

    public Query13Response findQuery13Response(
            Long duration){
        List<Object[]> query13Result = phase2EntityManager.findQuery13Result(duration);
        List<Query13DTO> response = query13Result.stream()
                .map(result -> Query13DTO.from(
                        (Integer) result[0],
                        (Long) result[1],
                        (Integer) result[2]))
                .collect(Collectors.toUnmodifiableList());

        return new Query13Response(response);
    }
}
