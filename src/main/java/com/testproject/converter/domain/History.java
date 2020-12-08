package com.testproject.converter.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String origValute;
    private String targetValute;
    private double originalAmount;
    private double receivedAmount;
    private Date date;

    public History() {
    }

    public History(String origValute, String targetValute, double originalAmount, double receivedAmount, Date date) {
        this.origValute = origValute;
        this.targetValute = targetValute;
        this.originalAmount = originalAmount;
        this.receivedAmount = receivedAmount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigValute() {
        return origValute;
    }

    public void setOrigValute(String origValute) {
        this.origValute = origValute;
    }

    public String getTargetValute() {
        return targetValute;
    }

    public void setTargetValute(String targetValute) {
        this.targetValute = targetValute;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
