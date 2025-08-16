package com.pahanaedu.service.impl;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.dao.impl.ItemDAOImpl;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public void addItem(Item item) {
        itemDAO.insertItem(item);
    }

    @Override
    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    @Override
    public void deleteItem(int id) {
        itemDAO.deleteItem(id);
    }

    @Override
    public Item getItemById(int id) {
        return itemDAO.getItemById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }
}
