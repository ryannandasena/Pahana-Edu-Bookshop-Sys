package com.pahanaedu.dao;

import com.pahanaedu.model.Item;
import java.util.List;

public interface ItemDAO {
    void insertItem(Item item);
    void updateItem(Item item);
    void deleteItem(int id);
    Item getItemById(int id);
    List<Item> getAllItems();
}
