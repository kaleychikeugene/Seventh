package com.example.rosst.Last;


import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends ListFragment {

    interface ListListener {
        void itemClicked(long id);
    }

    private ListListener listListener;
    private List<Customer> customers= new ArrayList<>();

    public List_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Cursor cursor = new Container().getCursor();
        if (cursor.moveToFirst()) {
            Customer customer = new Customer(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)));
            customers.add(customer);
            while (cursor.moveToNext()) {
                customer = new Customer(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)));
                customers.add(customer);
            }
        }
        CursorAdapter cursorAdapter=null;
        try {
            cursorAdapter = new SimpleCursorAdapter(inflater.getContext(), R.layout.item,
                    cursor, new String[]{"_id","FIO","ADDRESS","CREDIT_CARD_NUMBER","BANK_ACCOUNT_NUMBER",
                    "CREDIT","PAYMENT","TIME_CREDIT","TIME_PAYMENT","DEBT_CREDIT","DEBT_PAYMENT"},
                    new int[]{R.id.t1,R.id.t2,R.id.t3,R.id.t4,R.id.t5,R.id.t6,R.id.t7,R.id.t8,R.id.t9,R.id.t10,R.id.t11}, 0);
        }catch (Exception e){
            Log.d("tag",e.getMessage());
        }
        setListAdapter(cursorAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listListener = (ListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listListener != null) {
            listListener.itemClicked(position);
        }
    }
}
