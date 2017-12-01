package com.example.rosst.Seventh;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Main4Activity extends AppCompatActivity implements ListViewFragment.ListListener, DetailFragment.DataPass {

    private Button button;
    private ListViewFragment listViewFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listViewFragment = new ListViewFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment, listViewFragment).commit();

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

        DataBase database = new DataBase(this);
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("CUSTOMERS", null, "_id = ?",
                new String[]{String.valueOf(id)},
                null, null, null);
        Customer customer=null;
        if (cursor.moveToFirst()) {
            customer = new Customer(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)));
        }
        cursor.close();
        bundle.putParcelable("customer", customer);
        detailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment, detailFragment).addToBackStack(null).commit();
    }

    @Override
    public void onDataPass() {
        ListViewFragment blankFragment = new ListViewFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment, blankFragment).commit();
        Cursor cursor = new Container().getCursor();
        cursor.requery();
    }
}