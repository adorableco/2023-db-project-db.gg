package KNU.MainServer.phase2.api;

import KNU.MainServer.phase2.response.Query10Response;
import KNU.MainServer.phase2.response.Query13Response;
import KNU.MainServer.phase2.response.Query3Response;
import KNU.MainServer.phase2.response.Query6Response;
import KNU.MainServer.phase2.response.Query7Response;
import KNU.MainServer.phase2.service.Phase2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phase2")
@RequiredArgsConstructor
public class Phase2Controller {
    private final Phase2Service phase2Service;

    @GetMapping("/query3")
    public ResponseEntity<Query3Response> getQuery3Result(
            @RequestParam(name = "kill") Integer kill){

        Query3Response response = phase2Service.findQuery3Response(kill);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/query6")
    public ResponseEntity<Query6Response> getQuery6Result
            (@RequestParam(name = "eventTime") Long eventTime){
        Query6Response response =phase2Service.findQuery6Response(eventTime);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/query7")
    public ResponseEntity<Query7Response> getQuery7Result
            (@RequestParam(name = "champName") String champName){
        Query7Response response =phase2Service.findQuery7Response(champName);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/query10")
    public ResponseEntity<Query10Response> getQuery10Result
            (@RequestParam(name = "matchId") String matchId){
        Query10Response response =phase2Service.findQuery10Response(matchId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/query13")
    public ResponseEntity<Query13Response> getQuery13Result
            (@RequestParam(name = "matchId") String matchId){
        Query13Response response =phase2Service.findQuery13Response(matchId);
        return ResponseEntity.ok(response);
    }

}
