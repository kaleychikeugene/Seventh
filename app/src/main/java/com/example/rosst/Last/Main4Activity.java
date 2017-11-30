package com.example.rosst.Last;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity implements List_Fragment.ListListener, DetailFragment.DataPass {
    private List<Customer> customers;
    private Button button;
    List_Fragment blankFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        blankFragment = new List_Fragment();
        getFragmentManager().beginTransaction().add(R.id.fragment, blankFragment).commit();

        button = (Button) findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment detailFragment = new DetailFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment, detailFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void itemClicked(long id) {
        Bundle bundle = new Bundle();
        DetailFragment detailFragment = new DetailFragment();

        customers = new ArrayList<>();
        Cursor cursor = new Container().getCursor();
        if (cursor.moveToFirst()) {
            Customer customer = new Customer(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)));
            customers.add(customer);
            while (cursor.moveToNext()) {
                customer = new Customer(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)));
                customers.add(customer);
            }
        }

        bundle.putParcelable("customer", customers.get((int) id));
        detailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment, detailFragment).addToBackStack(null).commit();

    }

    @Override
    public void onDataPass(Customer customerr) {
        List_Fragment blankFragment = new List_Fragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment, blankFragment).commit();
        Cursor cursor = new Container().getCursor();
        cursor.requery();
    }
}
