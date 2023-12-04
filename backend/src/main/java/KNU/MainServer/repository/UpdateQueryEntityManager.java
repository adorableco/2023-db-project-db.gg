package KNU.MainServer.repository;

import KNU.MainServer.domain.Item;
import KNU.MainServer.domain.Champion;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UpdateQueryEntityManager {
    private final EntityManager entityManager;

    @Transactional
    public void updateItem(Long itemId, String newName, String newDescription, Integer newPrice) {
        try {
            Item item = entityManager.find(Item.class, itemId);
            if (item != null) {
                item.setName(newName);
                item.setDescription(newDescription);
                item.setPrice(newPrice);
                entityManager.merge(item);
            } else {
                log.warn("No item found with ID: " + itemId);
            }
        } catch (DataAccessException e) {
            log.error("Error occurred while updating item with ID: " + itemId);
        } catch (Exception e) {
            log.error("Error occurred while updating item with ID: " + itemId);
        }
    }
    
    @Transactional
    public void updateChampion(Integer champId, String newChampName, String newChampPhoto) {
        try {
            Champion champion = entityManager.find(Champion.class, champId);
            if (champion != null) {
                champion.setChampName(newChampName);
                champion.setChampPhoto(newChampPhoto);
                entityManager.merge(champion);
            } else {
                log.warn("No champion found with ID: " + champId);
            }
        } catch (DataAccessException e) {
            log.error("Database access error occurred while updating champion with ID: " + champId);
        } catch (Exception e) {
            log.error("An error occurred while updating champion with ID: " + champId);
        }
    }
}
