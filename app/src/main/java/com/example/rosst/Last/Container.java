package com.example.rosst.Last;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by rosst on 28.11.2017.
 */

public class Container {
    private static Cursor cursor;
    private String qwe;
    public Container() {
    }

    public static void setCursor(Cursor cursor) {
        Container.cursor = cursor;
    }

    public static Cursor getCursor() {

        return cursor;
    }
}
