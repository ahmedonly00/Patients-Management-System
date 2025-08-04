package com.patients.patientsMgt.dto;

import com.patients.patientsMgt.model.Payment;

import java.time.LocalDate;

public class PaymentDTO {

    private Long paymentId;

    private Payment.PaymentType paymentType;

    private double amount;

    private Payment.PaymentMethod paymentMethod;

    private Payment.PaymentStatus paymentStatus;

    private LocalDate paymentDate;

    private String transactionId;

    private String notes;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public PaymentDTO() {
    }

    public PaymentDTO(Long paymentId, Payment.PaymentType paymentType, double amount, Payment.PaymentMethod paymentMethod, Payment.PaymentStatus paymentStatus, LocalDate paymentDate, String transactionId, String notes, LocalDate createdAt, LocalDate updatedAt) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.transactionId = transactionId;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }


    public Payment.PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Payment.PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Payment.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Payment.PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Payment.PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
