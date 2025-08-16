package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import java.util.List;

public interface BillDAO {
    int addBill(Bill bill); // returns generated bill_id
    void updateBillTotal(int billId, double totalAmount);

    void addBillItem(int billId, int itemId, int quantity, double price);
    List<BillItem> getItemsByBillId(int billId);

    Bill getBillById(int billId);
    List<Bill> getAllBills();

    void deleteBillItems(int billId);
    void deleteBill(int billId);
    
    double calculateTotalAmount(int billId);
}
