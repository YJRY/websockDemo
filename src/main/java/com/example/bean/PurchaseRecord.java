package com.example.bean;

import java.time.LocalDateTime;

public class PurchaseRecord {

    private Long id;
    private Long userId;
    private Long productId;
    private Double price;
    private Integer quantity;
    private Double sum;
    private LocalDateTime purchaseDate;
    private String note;

    public static PurchaseRecord init(long userId, Product product, int quantity) {
        PurchaseRecord record = new PurchaseRecord();
        record.setUserId(userId);
        record.setProductId(product.getId());
        double price = product.getPrice();
        record.setPrice(price);
        record.setQuantity(quantity);
        record.setSum(price * quantity);
        LocalDateTime now = LocalDateTime.now();
        record.setPurchaseDate(now);
        record.setNote("购买时间：" + now);
        return record;
    }

    public PurchaseRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
