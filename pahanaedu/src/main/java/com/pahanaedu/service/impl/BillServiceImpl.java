package com.pahanaedu.service.impl;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.impl.BillDAOImpl;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import com.pahanaedu.service.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {

    private final BillDAO billDAO = new BillDAOImpl();

    @Override
    public int addBill(Bill bill) {
        return billDAO.addBill(bill);
    }

    @Override
    public void updateBillTotal(int billId) {
        double total = billDAO.calculateTotalAmount(billId);
        billDAO.updateBillTotal(billId, total);
    }

    @Override
    public void addBillItem(int billId, int itemId, int quantity, double price) {
        billDAO.addBillItem(billId, itemId, quantity, price);
    }

    @Override
    public List<BillItem> getItemsByBillId(int billId) {
        return billDAO.getItemsByBillId(billId);
    }

    @Override
    public Bill getBillById(int billId) {
        Bill bill = billDAO.getBillById(billId);
        if (bill != null) {
            bill.setBillItems(billDAO.getItemsByBillId(billId));
            bill.setTotalAmount(billDAO.calculateTotalAmount(billId));
        }
        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = billDAO.getAllBills();
        for (Bill b : bills) {
            b.setBillItems(billDAO.getItemsByBillId(b.getBillId()));
            b.setTotalAmount(billDAO.calculateTotalAmount(b.getBillId()));
        }
        return bills;
    }

    @Override
    public void deleteBill(int billId) {
        billDAO.deleteBillItems(billId);
        billDAO.deleteBill(billId);
    }
}
