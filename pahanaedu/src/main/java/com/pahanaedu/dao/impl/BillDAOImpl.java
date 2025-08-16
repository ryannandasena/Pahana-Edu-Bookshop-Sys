package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public int addBill(Bill bill) {
        String sql = "INSERT INTO bills (customer_account_number, total_amount) VALUES (?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, bill.getCustomerAccountNumber());
            ps.setDouble(2, bill.getTotalAmount());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void addBillItem(int billId, int itemId, int quantity, double price) {
        String sql = "INSERT INTO bill_items (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            ps.setInt(2, itemId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BillItem> getItemsByBillId(int billId) {
        List<BillItem> items = new ArrayList<>();
        String sql = "SELECT bi.id, bi.item_id, i.item_name, bi.quantity, bi.price " +
                     "FROM bill_items bi " +
                     "JOIN items i ON bi.item_id = i.item_id " +
                     "WHERE bi.bill_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BillItem bi = new BillItem();
                    bi.setId(rs.getInt("id"));
                    bi.setItemId(rs.getInt("item_id"));
                    bi.setItemName(rs.getString("item_name"));
                    bi.setQuantity(rs.getInt("quantity"));
                    bi.setPrice(rs.getDouble("price"));
                    items.add(bi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Bill getBillById(int billId) {
        String sql = "SELECT * FROM bills WHERE bill_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Bill b = new Bill();
                    b.setBillId(rs.getInt("bill_id"));
                    b.setCustomerAccountNumber(rs.getInt("customer_account_number"));
                    b.setTotalAmount(rs.getDouble("total_amount"));
                    b.setBillDate(rs.getTimestamp("bill_date"));
                    return b;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills ORDER BY bill_id DESC";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Bill b = new Bill();
                b.setBillId(rs.getInt("bill_id"));
                b.setCustomerAccountNumber(rs.getInt("customer_account_number"));
                b.setTotalAmount(rs.getDouble("total_amount"));
                b.setBillDate(rs.getTimestamp("bill_date"));
                bills.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public double calculateTotalAmount(int billId) {
        double total = 0;
        String sql = "SELECT SUM(quantity * price) AS total FROM bill_items WHERE bill_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public void updateBillTotal(int billId, double totalAmount) {
        String sql = "UPDATE bills SET total_amount = ? WHERE bill_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, totalAmount);
            ps.setInt(2, billId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBillItems(int billId) {
        String sql = "DELETE FROM bill_items WHERE bill_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBill(int billId) {
        String sql = "DELETE FROM bills WHERE bill_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
