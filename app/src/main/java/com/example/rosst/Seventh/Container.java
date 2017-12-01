package com.example.rosst.Seventh;

import android.database.Cursor;

/**
 * Created by rosst on 28.11.2017.
 */

public class Container {
    private static Cursor cursor;
    public Container() {
    }

    public static void setCursor(Cursor cursor) {
        Container.cursor = cursor;
    }

    public static Cursor getCursor() {

        return cursor;
    }
}
