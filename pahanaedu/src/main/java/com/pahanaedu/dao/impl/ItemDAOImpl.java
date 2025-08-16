package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Item;
import com.pahanaedu.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public void insertItem(Item item) {
        String sql = "INSERT INTO items (item_name, price, stock_quantity) VALUES (?, ? ,?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getItemName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getUnitsInStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Item item) {
        String sql = "UPDATE items SET item_name = ?, price = ?, stock_quantity = ? WHERE item_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getItemName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getUnitsInStock());
            stmt.setInt(4, item.getItemId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(int id) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item getItemById(int id) {
        String sql = "SELECT * FROM items WHERE item_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Item(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                items.add(new Item(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
