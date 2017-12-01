package com.example.rosst.Seventh;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rosst on 21.11.2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super(context,"mydb",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("DROP TABLE IF EXISTS CUSTOMERS");
        db.execSQL("CREATE TABLE CUSTOMERS(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "FIO TEXT," +
                "ADDRESS TEXT," +
                "CREDIT_CARD_NUMBER INTEGER," +
                "BANK_ACCOUNT_NUMBER INTEGER," +
                "CREDIT INTEGER," +
                "PAYMENT INTEGER," +
                "TIME_CREDIT INTEGER," +
                "TIME_PAYMENT INTEGER," +
                "DEBT_CREDIT INTEGER," +
                "DEBT_PAYMENT INTEGER" +
                ");");

        db.insert("CUSTOMERS",null,insertCustomer("William Jacobson", "Cambodia", 2279, 7103,2000,8000,24,9,400,400));
        db.insert("CUSTOMERS",null,insertCustomer("Thomas Little", "Dominica", 1463, 5315,1000,4000,35,12,300,100));
        db.insert("CUSTOMERS",null,insertCustomer("Kent Peacock", "Venezuela", 4242, 846,2000,4000,15,20,0,0));
        db.insert("CUSTOMERS",null,insertCustomer("Roger Gill", "Bahrain", 1213, 2439,500,4000,46,14,50,400));
        db.insert("CUSTOMERS",null,insertCustomer("Douglas Fairy", "Austria", 1294, 18706,6000,400,34,10,0,0));
        db.insert("CUSTOMERS",null,insertCustomer("Boris Jones", "Spain", 572, 9362,2000,40000,25,14,150,400));
        db.insert("CUSTOMERS",null,insertCustomer("Alexander Chester", "Liechtenstein", 5613, 3162,2000,4000,12,36,300,700));
        db.insert("CUSTOMERS",null,insertCustomer("Adam Gordon", "Norway", 6596, 6359,20000,4000,33,22,200,600));
        db.insert("CUSTOMERS",null,insertCustomer("Matthew Turner", "Somalia", 7846, 4775,9000,6500,28,18,0,0));
        db.insert("CUSTOMERS",null,insertCustomer("Claudia Sinclair", "Switzerland", 4309, 8087,7000,400,14,6,900,400));
        db.insert("CUSTOMERS",null,insertCustomer("John Watson", "Switzerland", 4309, 8097,7060,400,36,12,2000,400));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ContentValues insertCustomer(String fio, String address, int creditCardNumber, int bankAccountNumber, int credit, int payment, int timeCredit, int timePayment, int debtCredit, int debtPayment){
        ContentValues customer = new ContentValues();
        customer.put("FIO", fio);
        customer.put("ADDRESS", address);
        customer.put("CREDIT_CARD_NUMBER", creditCardNumber);
        customer.put("BANK_ACCOUNT_NUMBER", bankAccountNumber);
        customer.put("CREDIT", credit);
        customer.put("PAYMENT", payment);
        customer.put("TIME_CREDIT", timeCredit);
        customer.put("TIME_PAYMENT", timePayment);
        customer.put("DEBT_CREDIT", debtCredit);
        customer.put("DEBT_PAYMENT", debtPayment);
        return customer;
    }
}
