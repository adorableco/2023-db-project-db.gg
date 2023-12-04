package KNU.MainServer.api;


import KNU.MainServer.response.GameAccountResponse;
import KNU.MainServer.response.MatchDetailResponse;
import KNU.MainServer.response.MatchInfoResponse;
import KNU.MainServer.service.GameAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

}
