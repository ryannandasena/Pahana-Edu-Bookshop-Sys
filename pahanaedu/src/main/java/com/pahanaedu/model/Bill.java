package com.pahanaedu.model;

import java.sql.Timestamp;
import java.util.List;

public class Bill {

    private int billId;
    private int customerAccountNumber;
    private double totalAmount;
    private Timestamp billDate;

    // New fields for display
    private String customerName;          // Add this
    private List<BillItem> billItems;     // Add this

    // Getters and setters
    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getCustomerAccountNumber() { return customerAccountNumber; }
    public void setCustomerAccountNumber(int customerAccountNumber) { this.customerAccountNumber = customerAccountNumber; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Timestamp getBillDate() { return billDate; }
    public void setBillDate(Timestamp billDate) { this.billDate = billDate; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public List<BillItem> getBillItems() { return billItems; }
    public void setBillItems(List<BillItem> billItems) { this.billItems = billItems; }
}
