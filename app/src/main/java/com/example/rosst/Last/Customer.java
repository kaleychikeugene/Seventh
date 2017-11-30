package com.example.rosst.Last;

import android.os.Parcel;
import android.os.Parcelable;


public class Customer implements Parcelable{

    private long id;
    private String fio;
    private String address;
    private int creditCardNumber;
    private int bankAccountNumber;
    private int credit;
    private int payment;
    private int timeCredit;
    private int timePayment;
    private int debtCredit;
    private int debtPayment;

    protected Customer(Parcel in) {
        id = in.readLong();
        fio = in.readString();
        address = in.readString();
        creditCardNumber = in.readInt();
        bankAccountNumber = in.readInt();
        credit = in.readInt();
        payment = in.readInt();
        timeCredit = in.readInt();
        timePayment = in.readInt();
        debtCredit = in.readInt();
        debtPayment = in.readInt();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public int getTimeCredit() {
        return timeCredit;
    }

    public int getTimePayment() {
        return timePayment;
    }

    public int getCredit() {
        return credit;
    }

    public int getPayment() {
        return payment;
    }

    public int getDebtCredit() {
        return debtCredit;
    }

    public int getDebtPayment() {
        return debtPayment;
    }

    public Customer(long id, String fio, String address, int creditCardNumber, int bankAccountNumber, int credit, int payment, int timeCredit, int timePayment, int debtCredit, int debtPayment) {
        this.id = id;
        this.fio = fio;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.credit = credit;
        this.payment = payment;
        this.timeCredit = timeCredit;
        this.timePayment = timePayment;
        this.debtCredit = debtCredit;
        this.debtPayment = debtPayment;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setTimeCredit(int timeCredit) {
        this.timeCredit = timeCredit;
    }

    public void setTimePayment(int timePayment) {
        this.timePayment = timePayment;
    }

    public void setDebtCredit(int debtCredit) {
        this.debtCredit = debtCredit;
    }

    public void setDebtPayment(int debtPayment) {
        this.debtPayment = debtPayment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getFio() {
        return fio;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public Customer(String fio, String address, int creditCardNumber, int bankAccountNumber, int credit, int payment, int timeCredit, int timePayment, int debtCredit, int debtPayment) {
        this.fio = fio;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.credit = credit;
        this.payment = payment;
        this.timeCredit = timeCredit;
        this.timePayment = timePayment;
        this.debtCredit = debtCredit;
        this.debtPayment = debtPayment;
    }

    @Override
    public String toString() {
        return id +"; " + fio + "; " + address +"; №" + creditCardNumber +"; №" + bankAccountNumber +"; " + credit +"$; " + payment +"$; " + timeCredit +" month; " + timePayment +" month; " + debtCredit +"$; " + debtPayment+"$";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(fio);
        dest.writeString(address);
        dest.writeInt(creditCardNumber);
        dest.writeInt(bankAccountNumber);
        dest.writeInt(credit);
        dest.writeInt(payment);
        dest.writeInt(timeCredit);
        dest.writeInt(timePayment);
        dest.writeInt(debtCredit);
        dest.writeInt(debtPayment);
    }
}
