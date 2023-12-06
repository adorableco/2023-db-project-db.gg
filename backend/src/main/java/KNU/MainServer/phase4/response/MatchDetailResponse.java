package KNU.MainServer.phase4.response;

import KNU.MainServer.phase4.dto.EventDTO;
import KNU.MainServer.phase4.dto.ParticipantDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchDetailResponse {

    List<ParticipantDTO> summoners;
    List<EventDTO> events;
}
