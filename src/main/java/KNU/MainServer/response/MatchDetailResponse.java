package KNU.MainServer.response;

import KNU.MainServer.dto.EventDTO;
import KNU.MainServer.dto.ParticipantDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchDetailResponse {

    List<ParticipantDTO> summoners;
    List<EventDTO> events;
}
