package com.example.rosst.Seventh;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class Main4Activity extends AppCompatActivity implements ListViewFragment.ListListener, DetailFragment.DataPass {

    private Container container;
    private Cursor cursor;
    private DetailFragment detailFragment;
    private ListViewFragment listViewFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        cursor = container.getCursor();
        if (cursor == null) {
            detailFragment = new DetailFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment, detailFragment).commit();
        } else {
            listViewFragment = new ListViewFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment, listViewFragment).commit();
        }
    }

    @Override
    public void itemClicked(long id) {
        Bundle bundle = new Bundle();
        DetailFragment detailFragment = new DetailFragment();

        DataBase database = new DataBase(this);
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        cursor = sqLiteDatabase.query("CUSTOMERS", null, "_id = ?",
                new String[]{String.valueOf(id)},
                null, null, null);
        Customer customer = null;
        if (cursor.moveToFirst()) {
            customer = new Customer(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)));
        }
        cursor.close();
        bundle.putParcelable("customer", customer);
        detailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment, detailFragment).addToBackStack("detail").commit();
    }

    @Override
    public void onDataPass() {
        if (listViewFragment != null) {
            listViewFragment = new ListViewFragment();
            getFragmentManager().popBackStack();
            getFragmentManager().beginTransaction().replace(R.id.fragment, listViewFragment).commit();
            cursor = container.getCursor();
            cursor.requery();
        } else {
            finish();
            Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_LONG).show();
        }
    }
}
