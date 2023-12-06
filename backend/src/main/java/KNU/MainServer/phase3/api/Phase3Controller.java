package KNU.MainServer.phase3.api;

import KNU.MainServer.global.type.TierType;
import KNU.MainServer.phase3.service.Phase3Service;
import KNU.MainServer.phase3.response.InsertChampionResponse;
import KNU.MainServer.phase3.response.InsertGameAccountResponse;
import KNU.MainServer.phase3.response.InsertItemResponse;
import KNU.MainServer.phase3.response.DeleteChampionResponse;
import KNU.MainServer.phase3.response.DeleteGameAccountResponse;
import KNU.MainServer.phase3.response.DeleteItemResponse;
import KNU.MainServer.phase3.response.UpdateChampionResponse;
import KNU.MainServer.phase3.response.UpdateGameAccountResponse;
import KNU.MainServer.phase3.response.UpdateItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/phase3")
public class Phase3Controller {
    private final Phase3Service phase3Service;

    @PostMapping("/createAccount")
    public ResponseEntity<InsertGameAccountResponse> insertGameAccount(
            @RequestParam(name = "gameName") String newName,
            @RequestParam(name = "accountLevel") Integer newLevel,
            @RequestParam(name = "tier") TierType newTier) { 

        InsertGameAccountResponse response = phase3Service.insertGameAccount(newName, newLevel, newTier);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/removeAccount")
    public ResponseEntity<DeleteGameAccountResponse> deleteGameAccount(
            @RequestParam (name = "uniqueGameAccountId")Long gameAccountId) {
                
        DeleteGameAccountResponse response = phase3Service.deleteGameAccount(gameAccountId);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/editAccount")
    public ResponseEntity<UpdateGameAccountResponse> updateGameAccount(
            @RequestParam(name = "uniqueGameAccountId") Long uniqueGameAccountId,
            @RequestParam(name = "gameName") String newName,
            @RequestParam(name = "accountLevel") Integer newLevel,
            @RequestParam(name = "tier") TierType newTier) { 

        UpdateGameAccountResponse response = phase3Service.updateGameAccount(uniqueGameAccountId, newName, newLevel, newTier);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createItem")
    public ResponseEntity<InsertItemResponse> insertItem(
            @RequestParam(name = "name") String newName,
            @RequestParam(name = "description") String newDescription,
            @RequestParam(name = "price") Integer newPrice) {

        InsertItemResponse response = phase3Service.insertItem(newName, newDescription, newPrice);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/removeItem")
    public ResponseEntity<DeleteItemResponse> deleteItem(
            @RequestParam(name = "itemId") Long itemId) {

        DeleteItemResponse response = phase3Service.deleteItem(itemId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/editItem")
    public ResponseEntity<ItemResponse> updateItem(
            @RequestParam(name = "itemId") Long itemId,
            @RequestParam(name = "name") String newName,
            @RequestParam(name = "description") String newDescription,
            @RequestParam(name = "price") Integer newPrice) {

        ItemResponse response = phase3Service.updateItem(itemId, newName, newDescription, newPrice);

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/createChampion")
    public ResponseEntity<InsertChampionResponse> insertChampion(
            @RequestParam(name = "champName") String newChampName,
            @RequestParam(name = "champPhoto") String newChampPhoto) { 

        InsertChampionResponse response = phase3Service.insertChampion(newChampName, newChampPhoto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/removeChampion")
    public ResponseEntity<DeleteChampionResponse> deleteChampion(
            @RequestParam(name = "uniqueChampId") Long champId) {

        DeleteChampionResponse response = phase3Service.deleteChampion(champId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/editChampion")
    public ResponseEntity<ChampionResponse> updateChampion(
            @RequestParam(name = "uniqueChampId") Long champId,
            @RequestParam(name = "champName") String newChampName,
            @RequestParam(name = "champPhoto") String newChampPhoto) { 

        UpdateChampionResponse response = phase3Service.updateChampion(champId, newChampName, newChampPhoto);

        return ResponseEntity.ok(response);
    }
}
