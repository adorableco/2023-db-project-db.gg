package KNU.MainServer.repository;

import KNU.MainServer.Type.TierType;
import KNU.MainServer.domain.GameAccount;
import KNU.MainServer.domain.Item;
import KNU.MainServer.domain.Champion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class InsertQueryEntityManager {
    private final EntityManager em;

    @Transactional
    public void insertGameAccount(String newName, Integer newLevel, TierType newTier) {
        try {
            GameAccount gameAccount = new GameAccount();
            gameAccount.setGameName(newName);
            gameAccount.setAccountLevel(newLevel);
            gameAccount.setTier(newTier);

            em.persist(gameAccount);
        } catch (PersistenceException e) {
            log.error("Error occurred while inserting new GameAccount", e);
            throw e;
        }
    }

    @Transactional
    public void insertItem(String newItemName, String newDescription, Integer newPrice) {
        try {
            Item item = new Item();
            item.setName(newItemName);
            item.setDescription(newDescription);
            item.setPrice(newPrice);

            em.persist(item);
        } catch (PersistenceException e) {
            log.error("Error occurred while inserting new Item", e);
            throw e;
        }
    }
    
    @Transactional
    public void insertChampion(String newChampName, String newChampPhoto) {
        try {
            Champion champion = new Champion();
            champion.setChampName(newChampName);
            champion.setChampPhoto(newChampPhoto);

            em.persist(champion);
        } catch (PersistenceException e) {
            log.error("Error occurred while inserting new Champion", e);
            throw e;
        }
    }
}
