package KNU.MainServer.repository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DeleteQueryEntityManager {
    private final EntityManager em;

    @Transactional
    public void deleteGameAccount(Long gameAccountId) {
        try {
            GameAccount gameAccount = em.find(GameAccount.class, gameAccountId);
            if (gameAccount != null) {
                em.remove(gameAccount);
            } else {
                log.warn("No GameAccount found with ID: " + gameAccountId);
            }
        } catch (PersistenceException e) {
            log.error("Error occurred while deleting GameAccount by id", e);
            throw e;
        }
    }

    @Transactional
    public void deleteItem(Long itemId) {
        try {
            Item item = em.find(Item.class, itemId);
            if (item != null) {
                em.remove(item);
            } else {
                log.warn("No Item found with ID: " + itemId);
            }
        } catch (PersistenceException e) {
            log.error("Error occurred while deleting Item by id", e);
            throw e;
        }
    }

    @Transactional
    public void deleteChampion(Long champId) {
        try {
            Champion champion = em.find(Champion.class, champId);
            if (champion != null) {
                em.remove(champion);
            } else {
                log.warn("No Champion found with ID: " + champId);
            }
        } catch (PersistenceException e) {
            log.error("Error occurred while deleting Champion by id", e);
            throw e;
        }
    }
}

