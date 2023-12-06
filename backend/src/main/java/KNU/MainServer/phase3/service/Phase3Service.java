package KNU.MainServer.phase3.service;

import KNU.MainServer.global.domain.Champion;
import KNU.MainServer.global.domain.GameAccount;
import KNU.MainServer.global.domain.Item;
import KNU.MainServer.global.type.TierType;
import KNU.MainServer.phase3.dto.GameAccountDTO;
import KNU.MainServer.phase3.dto.ItemDTO;
import KNU.MainServer.phase3.dto.ChampionDTO;
import KNU.MainServer.phase3.repository.Phase3EntityManager;
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
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Phase3Service {
    private final Phase3EntityManager phase3EntityManager;

    
    public InsertGameAccountResponse insertGameAccount(String newName, Integer newLevel, TierType newTier) {
        GameAccount gameAccount = phase3EntityManager.insertGameAccount(newName, newLevel, newTier);
        if (gameAccount == null) {
            return null;
        }

        GameAccountDTO gameAccountDTO = GameAccountDTO.from(gameAccount);
        return new InsertGameAccountResponse(gameAccountDTO);
    }

    
    public DeleteGameAccountResponse deleteGameAccount(Long gameAccountId) {
        try {
            phase3EntityManager.deleteGameAccount(gameAccountId);
            return new DeleteGameAccountResponse(gameAccountId);
        } catch (Exception e) {
            log.error("Failed to remove a GameAccount", e);
            throw e;
        }
    }

    public UpdateGameAccountResponse updateGameAccount(Long uniqueGameAccountId, String newName, Integer newLevel, TierType newTier) {
        GameAccount existingGameAccount = phase3EntityManager.updateGameAccount(uniqueGameAccountId, newName, newLevel, newTier);
        if(existingGameAccount == null) {
            return null;
        }
        
        GameAccountDTO gameAccountDTO = GameAccountDTO.from(existingGameAccount);
        return new UpdateGameAccountResponse(gameAccountDTO);
    }

    public InsertItemResponse insertItem
            (String newItemName, String newDescription, Integer newPrice) {
        Item item = phase3EntityManager.insertItem(newItemName, newDescription, newPrice);
        if(item == null){
            return null;
        }

        ItemDTO itemDTO = ItemDTO.from(item);
        return new InsertItemResponse(itemDTO);
    }

    public DeleteItemResponse deleteItem(Long itemId) {
        try {
            phase3EntityManager.deleteItem(itemId);
            return new DeleteItemResponse(itemId);
        } catch (Exception e) {
            log.error("Failed to remove a item", e);
            throw e;
        }
    }

    public UpdateItemResponse updateItem(Long itemId, String newItemName, String newDescription, Integer newPrice) {
        Item existingItem = phase3EntityManager.updateItem(itemId, newItemName, newDescription, newPrice);
        if(existingItem == null) {
            return null;
        }
        
        ItemDTO itemDTO = ItemDTO.from(existingItem);
        return new UpdateItemResponse(itemDTO);
    }

    public InsertChampionResponse insertChampion(String newChampName, String newChampPhoto) {
        Champion champion = phase3EntityManager.insertChampion(newChampName, newChampPhoto);
        if(champion == null){
            return null;
        }

        ChampionDTO championDTO = ChampionDTO.from(champion);
        return new InsertChampionResponse(championDTO);
    }

    public DeleteChampionResponse deleteChampion(Long champId) {
        try {
            phase3EntityManager.deleteChampion(champId);
            return new DeleteChampionResponse(champId);
        } catch (Exception e) {
            log.error("Failed to remove a champion", e);
            throw e;
        }
    }

    public UpdateChampionResponse updateChampion(Long uniqueChampId, String newChampName, String newChampPhoto) {
        Champion existingChampion = phase3EntityManager.updateChampion(uniqueChampId, newChampName, newChampPhoto);
        if(existingChampion == null) {
            return null;
        }
        
        ChampionDTO championDTO = ChampionDTO.from(existingChampion);
        return new UpdateChampionResponse(championDTO);
    }
}