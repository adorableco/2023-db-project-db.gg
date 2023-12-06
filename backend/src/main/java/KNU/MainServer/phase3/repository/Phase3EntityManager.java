package KNU.MainServer.phase3.repository;

import KNU.MainServer.global.domain.GameAccount;
import KNU.MainServer.global.domain.Item;
import KNU.MainServer.global.domain.Champion;
import KNU.MainServer.global.type.TierType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class Phase3EntityManager {
    private final EntityManager em;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GameAccount insertGameAccount(String newName, Integer newLevel, TierType newTier) {
        try {
            GameAccount gameAccount = new GameAccount();
            gameAccount.setGameName(newName);
            gameAccount.setAccountLevel(newLevel);
            gameAccount.setTier(newTier);

            em.persist(gameAccount);

            return gameAccount;
        } catch (PersistenceException e) {
            log.error("Error occurred while inserting new GameAccount", e);
            throw e;
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long deleteGameAccount(Long gameAccountId) {
        try {
            GameAccount gameAccount = em.find(GameAccount.class, gameAccountId);
            if (gameAccount != null) {
                em.remove(gameAccount);
                return gameAccountId;
            } else {
                log.warn("No GameAccount found with ID: " + gameAccountId);
                return null;
            }
        } catch (PersistenceException e) {
            log.error("Error occurred while deleting GameAccount by id", e);
            throw e;
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GameAccount updateGameAccount(Long uniqueGameAccountId, String newName, Integer newLevel, TierType newTier) {
        try {
            GameAccount gameAccount = em.find(GameAccount.class, uniqueGameAccountId);
            gameAccount.setGameName(newName);
            gameAccount.setAccountLevel(newLevel);
            gameAccount.setTier(newTier);

            em.merge(gameAccount);
            return gameAccount;
        } catch (PersistenceException e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Item insertItem(String newItemName, String newDescription, Integer newPrice) {
        try {
            Item item = new Item();
            item.setName(newItemName);
            item.setDescription(newDescription);
            item.setPrice(newPrice);

            em.persist(item);
            
            return item;
        } catch (PersistenceException e) {
            throw e;
        }
    }
    
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long deleteItem(Long itemId) {
        try {
            Item item = em.find(Item.class, itemId);
            if (item != null) {
                em.remove(item);
                return itemId;
            } else {
                log.warn("No Item found with ID: " + itemId);
                return null;
            }
        } catch (PersistenceException e) {
            log.error("Error occurred while deleting Item by id", e);
            throw e;
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Item updateItem(Long itemId, String newItemName, String newDescription, Integer newPrice) {
        try {
            Item item = em.find(Item.class, itemId);
            item.setName(newItemName);
            item.setDescription(newDescription);
            item.setPrice(newPrice);

            em.merge(item);
            return item;
        } catch (PersistenceException e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Champion insertChampion(String newChampName, String newChampPhoto) {
        try {
            Champion champion = new Champion();
            champion.setChampName(newChampName);
            champion.setChampPhoto(newChampPhoto);

            em.persist(champion);
            
            return champion;
        } catch (PersistenceException e) {
            log.error("Error occurred while inserting new Champion", e);
            throw e;
        }
    }
    
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long deleteChampion(Long champId) {
        try {
            Champion champion = em.find(Champion.class, champId);
            if (champion != null) {
                em.remove(champion);
                return champId;
            } else {
                log.warn("No Champion found with ID: " + champId);
                return null;
            }
        } catch (PersistenceException e) {
            log.error("Error occurred while deleting Champion by id", e);
            throw e;
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Champion updateChampion(Long champId, String newChampName, String newChampPhoto) {
        try {
            Champion champion = em.find(Champion.class, champId);
            champion.setChampName(newChampName);
            champion.setChampPhoto(newChampPhoto);

            em.merge(champion);
            return champion;
        } catch (PersistenceException e) {
            throw e;
        }
    }
}
