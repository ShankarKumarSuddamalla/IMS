package com.tcs.reportservice.services;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.tcs.reportservice.clients.InventoryClient;
import com.tcs.reportservice.clients.OrderClient;
import com.tcs.reportservice.clients.ProductClient;
import com.tcs.reportservice.clients.UserClient;
import com.tcs.reportservice.dtos.InventoryDTO;
import com.tcs.reportservice.dtos.OrderDTO;
import com.tcs.reportservice.dtos.ProductDTO;
import com.tcs.reportservice.dtos.UserDTO;

@Service
public class ReportService {

    private final ProductClient productClient;
    private final InventoryClient inventoryClient;
    private final OrderClient orderClient;
    private final UserClient userClient;

    public ReportService(ProductClient productClient,
                         InventoryClient inventoryClient,
                         OrderClient orderClient,
                         UserClient userClient) {
        this.productClient = productClient;
        this.inventoryClient = inventoryClient;
        this.orderClient = orderClient;
        this.userClient = userClient;
    }

    public byte[] generateFullReport() throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("INVENTORY MANAGEMENT SYSTEM REPORT"));
        document.add(new Paragraph(" "));

        // PRODUCTS
        document.add(new Paragraph("PRODUCTS"));
        for (ProductDTO p : productClient.getProducts()) {
            document.add(new Paragraph(
                p.getName() + " | " + p.getCategory() + " | ₹" + p.getPrice()
            ));
        }

        document.add(new Paragraph(" "));

        // INVENTORY
        document.add(new Paragraph("INVENTORY"));
        for (InventoryDTO i : inventoryClient.getInventory()) {
            document.add(new Paragraph(
                "Product ID: " + i.getProductId() +
                " | Quantity: " + i.getAvailableQuantity()
            ));
        }

        document.add(new Paragraph(" "));

        // ORDERS
        document.add(new Paragraph("ORDERS"));
        for (OrderDTO o : orderClient.getOrders()) {
            document.add(new Paragraph(
                "Order #" + o.getId() +
                " | " + o.getCustomerEmail() +
                " | ₹" + o.getTotalAmount()
            ));
        }

        document.add(new Paragraph(" "));

        // USERS
        document.add(new Paragraph("CUSTOMERS"));
        for (UserDTO u : userClient.getCustomers()) {
            document.add(new Paragraph(u.getEmail()));
        }

        document.add(new Paragraph("SUPPLIERS"));
        for (UserDTO u : userClient.getSuppliers()) {
            document.add(new Paragraph(u.getEmail()));
        }

        document.close();
        return out.toByteArray();
    }
}

