package entities;

import android.provider.ContactsContract;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by chezkiaho on 23.3.2018.
 */

public class Consumer {
    protected String lastName ;
    protected String firstName ;
    protected int id;
    protected Long phoneNumber ;
    protected String email ;
    protected  CreditCard creditCard;

    public Consumer(String lastName, String firstName, int id, Long phoneNumber, String email, CreditCard creditCard) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creditCard = creditCard;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }



    class CreditCard{
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

}
