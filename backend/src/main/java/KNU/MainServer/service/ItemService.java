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
public class ItemService {
    private final InsertQueryEntityManager insertQueryEntityManager;
    private final DeleteQueryEntityManager deleteQueryEntityManager;
    
    @Transactional
    public void createItem
            (Integer newItemID, String newItemName, String newDescription, Integer newPrice) {
        try {
            insertQueryEntityManager.insertItem(newItemID, newItemName, newDescription, newPrice);
        } catch (Exception e) {
            log.error("Failed to create a new item", e);
            throw e;
        }
    }

    @Transactional
    public void removeItem
            (Integer newItemID) {
        try {
            deleteQueryEntityManager.deleteItem(newItemID);
        } catch (Exception e) {
            log.error("Failed to remove a item", e);
            throw e;
        }
    }

    @Transactional
    public void updateItem
            (Integer newItemID, String ItemName, String Description, Integer Price) {
        try {
            insertQueryEntityManager.updatetItem(ItemID, ItemName, Description, Price);
        } catch (Exception e) {
            log.error("Failed to create a new item", e);
            throw e;
        }
    }
}