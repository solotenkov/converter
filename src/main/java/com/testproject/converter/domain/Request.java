package com.testproject.converter.domain;

import org.springframework.stereotype.Component;

@Component
public class Request {

    private String origValute;
    private String targetValute;
    private double originalAmount;
    private double receivedAmount;

    @Override
    public String toString() {
        return "Request{" +
                "origValute='" + origValute + '\'' +
                ", targetValute='" + targetValute + '\'' +
                ", originalAmount=" + originalAmount +
                ", receivedAmount=" + receivedAmount +
                '}';
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
}
