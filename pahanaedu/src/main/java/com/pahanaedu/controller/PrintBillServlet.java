package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import com.pahanaedu.service.BillService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import com.pahanaedu.service.impl.BillServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/print-bill")
public class PrintBillServlet extends HttpServlet {

	private BillService billService = new BillServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bill ID is required");
            return;
        }

        int billId = Integer.parseInt(idParam);
        Bill bill = billService.getBillById(billId);

        if (bill == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Bill not found");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=bill_" + billId + ".pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            document.add(new Paragraph("Invoice", titleFont));
            document.add(new Paragraph(" ")); // Spacer

            // Bill Info
            document.add(new Paragraph("Bill ID: " + bill.getBillId()));
            document.add(new Paragraph("Customer Account No: " + bill.getCustomerAccountNumber()));
            document.add(new Paragraph("Date: " + bill.getBillDate()));
            document.add(new Paragraph(" "));

            // Table Header
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            String[] headers = {"Item", "Quantity", "Price", "Subtotal"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(cell);
            }

            // Table Data
            for (BillItem item : bill.getBillItems()) {
                table.addCell(item.getItemName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.format("%.2f", item.getPrice()));
                table.addCell(String.format("%.2f", item.getQuantity() * item.getPrice()));
            }

            document.add(table);

            // Total
            Font totalFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            document.add(new Paragraph("Total: Rs. " + bill.getTotalAmount(), totalFont));

            document.close();

        } catch (Exception e) {
            throw new ServletException("Error generating PDF", e);
        }
    }
}
