package pahanaedu;

import org.junit.jupiter.api.Test;

import com.pahanaedu.model.Item;
import com.pahanaedu.dao.impl.ItemDAOImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDAOImplTest {
	 private final ItemDAOImpl itemDAO = new ItemDAOImpl();


    @Test
    public void testInsertItem() {
        Item item = new Item();
        item.setItemName("Test book");
        item.setPrice(1200.0);
        item.setUnitsInStock(5);

        itemDAO.insertItem(item);

        List<Item> items = itemDAO.getAllItems();
        boolean exists = items.stream()
                .anyMatch(i -> "Test book".equals(i.getItemName()) && i.getPrice() == 1200.0);

        assertTrue(exists, "Item should be added successfully");
    }

    @Test
    public void testUpdateItem() {
        // Add item first
        Item item = new Item();
        item.setItemName("Test pen");
        item.setPrice(25.0);
        item.setUnitsInStock(50);
        itemDAO.insertItem(item);

        // Find inserted item
        Item saved = itemDAO.getAllItems().stream()
                .filter(i -> "Test pen".equals(i.getItemName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found after insert"));

        // Update item
        saved.setItemName("Update Test pen");
        saved.setPrice(30.0);
        saved.setUnitsInStock(45);
        itemDAO.updateItem(saved);

        // Verify update
        Item updated = itemDAO.getItemById(saved.getItemId());
        assertEquals("Update Test pen", updated.getItemName());
        assertEquals(30.0, updated.getPrice());
        assertEquals(45, updated.getUnitsInStock());
    }

    @Test
    public void testDeleteItem() {
        // Add item first
        Item item = new Item();
        item.setItemName("Test bookA");
        item.setPrice(50.0);
        item.setUnitsInStock(20);
        itemDAO.insertItem(item);

        // Find inserted item
        Item saved = itemDAO.getAllItems().stream()
                .filter(i -> "Test bookA".equals(i.getItemName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found after insert"));

        // Delete item
        itemDAO.deleteItem(saved.getItemId());

        // Verify deletion
        List<Item> items = itemDAO.getAllItems();
        boolean exists = items.stream()
                .anyMatch(i -> "Test bookA".equals(i.getItemName()));
        assertFalse(exists, "Item should be deleted successfully");
    }

    @Test
    public void testGetAllItems() {
        // Add multiple items
        Item item1 = new Item();
        item1.setItemName("Abook");
        item1.setPrice(300.0);
        item1.setUnitsInStock(10);
        itemDAO.insertItem(item1);

        Item item2 = new Item();
        item2.setItemName("Bbook");
        item2.setPrice(150.0);
        item2.setUnitsInStock(5);
        itemDAO.insertItem(item2);

        // Fetch all items
        List<Item> items = itemDAO.getAllItems();

        boolean hasMonitor = items.stream()
                .anyMatch(i -> "Abook".equals(i.getItemName()) && i.getPrice() == 300.0);

        boolean hasPrinter = items.stream()
                .anyMatch(i -> "Bbook".equals(i.getItemName()) && i.getPrice() == 150.0);

        assertTrue(hasMonitor, "Abook should exist in the list");
        assertTrue(hasPrinter, "Bbook should exist in the list");

        assertTrue(items.size() >= 2, "There should be at least 2 items");
    }
}
