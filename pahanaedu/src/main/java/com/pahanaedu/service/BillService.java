package com.pahanaedu.service;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import java.util.List;

public interface BillService {
    int addBill(Bill bill);
    void updateBillTotal(int billId);

    void addBillItem(int billId, int itemId, int quantity, double price);
    List<BillItem> getItemsByBillId(int billId);

    Bill getBillById(int billId);
    List<Bill> getAllBills();

    void deleteBill(int billId);
}
