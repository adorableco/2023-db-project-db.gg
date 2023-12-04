package KNU.MainServer.service;

import KNU.MainServer.repository.InsertQueryEntityManager;
import KNU.MainServer.repository.DeleteQueryEntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChampionService {
    private final InsertQueryEntityManager insertQueryEntityManager;
    private final DeleteQueryEntityManager deleteQueryEntityManager;


    @Transactional
    public void createChampion
            (Integer newChampID, String newChampName, String newChampPhoto) {
        try {
            insertQueryEntityManager.insertChampion(newChampID, newChampName, newChampPhoto);
        } catch (Exception e) {
            log.error("Failed to create a new champion", e);
            throw e;
        }
    }
    
    @Transactional
    public void removeChampion
            (Integer newChampID) {
        try {
            //
            deleteQueryEntityManager.deleteChampion(newChampID);
        } catch (Exception e) {
            log.error("Failed to remove a champion", e);
            throw e;
        }

        
    @Transactional
    public void updateChampion
            (Integer newChampID, String newChampName, String newChampPhoto) {
        try {
            insertQueryEntityManager.updateChampion(newChampID, newChampName, newChampPhoto);
        } catch (Exception e) {
            log.error("Failed to update a new champion", e);
            throw e;
        }
    }
}