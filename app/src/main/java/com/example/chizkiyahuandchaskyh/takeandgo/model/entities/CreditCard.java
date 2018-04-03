package com.example.chizkiyahuandchaskyh.takeandgo.model.entities;

public class CreditCard{
    Long number;
    int expirMonth;
    int expirYear;
    int cvv;

    public CreditCard(Long number, int expirMonth, int expirYear, int cvv) {
        this.number = number;
        this.expirMonth = expirMonth;
        this.expirYear = expirYear;
        this.cvv = cvv;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getExpirMonth() {
        return expirMonth;
    }

    public void setExpirMonth(int expirMonth) {
        this.expirMonth = expirMonth;
    }

    public int getExpirYear() {
        return expirYear;
    }

    public void setExpirYear(int expirYear) {
        this.expirYear = expirYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }



}