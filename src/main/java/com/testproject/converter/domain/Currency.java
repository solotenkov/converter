package com.testproject.converter.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String valuteId;
    private Date date;
    private int numCode;
    private String charCode;

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", valuteId='" + valuteId + '\'' +
                ", date=" + date +
                ", numCode=" + numCode +
                ", charCode='" + charCode + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public Currency() {
    }

    public Currency(Date date) {
        this.date = date;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    private int nominal;
    private String name;
    private double value;


    public String getValuteId() {
        return valuteId;
    }

    public void setValuteId(String valuteId) {
        this.valuteId = valuteId;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
