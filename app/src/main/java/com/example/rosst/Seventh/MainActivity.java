package com.example.rosst.Seventh;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private AutoCompleteTextView autoCompleteTextView1;
    private AutoCompleteTextView autoCompleteTextView2;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    private DataBase database;
    private SQLiteDatabase sqLiteDatabase;

    private Container container = new Container();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            switch (item.getItemId()) {
                case R.id.item1: {
                    container.setCursor(null);
                    Intent intent=new Intent(this,Main4Activity.class);
                    startActivity(intent);
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DataBase(this);
        sqLiteDatabase = database.getReadableDatabase();

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqLiteDatabase.query("CUSTOMERS", null, null, null, null, null, "FIO ASC");
                container.setCursor(cursor);
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });

        String[] autoList = getResources().getStringArray(R.array.string_array);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, autoList);
        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autoCompleteTextView1.setAdapter(arrayAdapter);
        autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        autoCompleteTextView2.setAdapter(arrayAdapter);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                Cursor cursor = sqLiteDatabase.query("CUSTOMERS", null, "CREDIT_CARD_NUMBER > ? AND CREDIT_CARD_NUMBER < ?",
                        new String[]{autoCompleteTextView1.getText().toString(), autoCompleteTextView2.getText().toString()},
                        null, null, null);
                container.setCursor(cursor);
                startActivity(intent);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqLiteDatabase.query("CUSTOMERS", null, null, null, null, null, null);
                container.setCursor(cursor);
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.map: {
                        Intent intent = new Intent(MainActivity.this, Main5Activity.class);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqLiteDatabase.query("CUSTOMERS", null, "CREDIT = ? AND PAYMENT = ?",
                        new String[]{editText1.getText().toString(), editText2.getText().toString()},
                        null, null, null);
                container.setCursor(cursor);
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqLiteDatabase.query("CUSTOMERS", null, "TIME_CREDIT = ? AND TIME_PAYMENT = ?",
                        new String[]{editText3.getText().toString(), editText4.getText().toString()},
                        null, null, null);
                container.setCursor(cursor);
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqLiteDatabase.query("CUSTOMERS", null, "DEBT_CREDIT != 0 AND DEBT_PAYMENT != 0",
                        null,
                        null, null, null);
                container.setCursor(cursor);
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CUSTOMERS WHERE DEBT_CREDIT > CREDIT*0.25 " +
                        "AND DEBT_PAYMENT > PAYMENT*0.25", null);
                container.setCursor(cursor);
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });
    }
}
