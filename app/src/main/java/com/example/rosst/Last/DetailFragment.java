package com.example.rosst.Last;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DetailFragment extends Fragment implements View.OnClickListener {

    public interface DataPass {
        void onDataPass(Customer customer);
    }

    private DataPass dataPass;
    private Customer customer;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private EditText editText7;
    private EditText editText8;
    private EditText editText9;
    private EditText editText10;
    private Button button1;
    private Button button2;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private int choose = 0;
    private DataBase database;
    private SQLiteDatabase sqLiteDatabase;

    public DetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        Bundle bundle = getArguments();
        if (bundle != null)
            customer = bundle.getParcelable("customer");

        database = new DataBase(getContext());
        sqLiteDatabase = database.getReadableDatabase();

        ButtonFragment buttonFragment = new ButtonFragment();
        getChildFragmentManager().beginTransaction().add(R.id.button_container, buttonFragment).commit();
        builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (choose == 0) {
                    if (customer != null) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("FIO", editText1.getText().toString());
                        contentValues.put("ADDRESS", editText2.getText().toString());
                        contentValues.put("CREDIT_CARD_NUMBER", Integer.parseInt(editText3.getText().toString()));
                        contentValues.put("BANK_ACCOUNT_NUMBER", Integer.parseInt(editText4.getText().toString()));
                        contentValues.put("CREDIT", Integer.parseInt(editText5.getText().toString()));
                        contentValues.put("PAYMENT", Integer.parseInt(editText6.getText().toString()));
                        contentValues.put("TIME_CREDIT", Integer.parseInt(editText7.getText().toString()));
                        contentValues.put("TIME_PAYMENT", Integer.parseInt(editText8.getText().toString()));
                        contentValues.put("DEBT_CREDIT", Integer.parseInt(editText9.getText().toString()));
                        contentValues.put("DEBT_PAYMENT", Integer.parseInt(editText10.getText().toString()));
                        sqLiteDatabase.update("CUSTOMERS", contentValues, "_id = ?", new String[]{String.valueOf(customer.getId())});
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("FIO", editText1.getText().toString());
                        contentValues.put("ADDRESS", editText2.getText().toString());
                        contentValues.put("CREDIT_CARD_NUMBER", Integer.parseInt(editText3.getText().toString()));
                        contentValues.put("BANK_ACCOUNT_NUMBER", Integer.parseInt(editText4.getText().toString()));
                        contentValues.put("CREDIT", Integer.parseInt(editText5.getText().toString()));
                        contentValues.put("PAYMENT", Integer.parseInt(editText6.getText().toString()));
                        contentValues.put("TIME_CREDIT", Integer.parseInt(editText7.getText().toString()));
                        contentValues.put("TIME_PAYMENT", Integer.parseInt(editText8.getText().toString()));
                        contentValues.put("DEBT_CREDIT", Integer.parseInt(editText9.getText().toString()));
                        contentValues.put("DEBT_PAYMENT", Integer.parseInt(editText10.getText().toString()));
                        sqLiteDatabase.insert("CUSTOMERS", null, contentValues);
                    }
                    dataPass.onDataPass(customer);
                } else {
                    sqLiteDatabase.delete("CUSTOMERS", "_id = ?", new String[]{String.valueOf(customer.getId())});

                    dataPass.onDataPass(null);
                }
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {

            button1 = (Button) getActivity().findViewById(R.id.button_Create);
            button2 = (Button) getActivity().findViewById(R.id.button_Delete);
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            editText1 = (EditText) view.findViewById(R.id.editText1);
            editText2 = (EditText) view.findViewById(R.id.editText2);
            editText3 = (EditText) view.findViewById(R.id.editText3);
            editText4 = (EditText) view.findViewById(R.id.editText4);
            editText5 = (EditText) view.findViewById(R.id.editText5);
            editText6 = (EditText) view.findViewById(R.id.editText6);
            editText7 = (EditText) view.findViewById(R.id.editText7);
            editText8 = (EditText) view.findViewById(R.id.editText8);
            editText9 = (EditText) view.findViewById(R.id.editText9);
            editText10 = (EditText) view.findViewById(R.id.editText10);
            Bundle bundle = getArguments();
            if (bundle != null) {
                customer = bundle.getParcelable("customer");
                editText1.setText(customer.getFio());
                editText2.setText(customer.getAddress());
                editText3.setText(String.valueOf(customer.getBankAccountNumber()));
                editText4.setText(String.valueOf(customer.getCreditCardNumber()));
                editText5.setText(String.valueOf(customer.getCredit()));
                editText6.setText(String.valueOf(customer.getPayment()));
                editText7.setText(String.valueOf(customer.getTimeCredit()));
                editText8.setText(String.valueOf(customer.getTimePayment()));
                editText9.setText(String.valueOf(customer.getDebtCredit()));
                editText10.setText(String.valueOf(customer.getDebtPayment()));

            } else {
                button1.setText("Add");
                button2.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Create: {
                choose = 0;
                if (button2.getVisibility() == View.VISIBLE)
                    builder.setMessage("Change?");
                else
                    builder.setMessage("Add?");
                alertDialog = builder.create();
                alertDialog.show();
            }
            break;
            case R.id.button_Delete: {
                choose = 1;
                builder.setMessage("Delete?");
                alertDialog = builder.create();
                alertDialog.show();
            }
            break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.dataPass = (DataPass) getActivity();
    }
}
