package com.example.projectv4.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import com.example.projectv4.data.AppContract;
import com.example.projectv4.data.PlayerDbHelper;
import com.example.projectv4.ui.LoginActivity;
import com.example.projectv4.ui.SignupActivity;

import java.util.ArrayList;

public final class Controller {
    private static Controller controller = null;
    private static PlayerDbHelper playerDbHelper = null;
    private static SQLiteDatabase Db = null;
    private static Context c = null;
    private static long id;
    private static Cursor cursor = null;
    private static ArrayList<String> names = new ArrayList<String>(); // for adapter

    static String[] projectionPass = {AppContract.Player._ID,
            AppContract.Player.COLUMN_NAME,
            AppContract.Player.COLUMN_PASSWORD
    };
    static String[] projectionRole = {AppContract.Player._ID,
            AppContract.Player.COLUMN_NAME,
            AppContract.Player.COLUMN_ROLE
    };
    static String selection = AppContract.Player.COLUMN_NAME + " = ?";
    static String selectionRole = AppContract.Player.COLUMN_ROLE + " = ?";
    static String[] selectionArgs = {"selector"};

    public static Controller getInstance() {
        if (Controller.controller == null)
            controller = new Controller();
        return controller;
    }

    public static void setContext(Context c) {
        Controller.c = c;
    }

    public static long setData(String TABLE_NAME, ContentValues values) {
        playerDbHelper = new PlayerDbHelper(c);
        Db = playerDbHelper.getWritableDatabase();
        id = Db.insert(TABLE_NAME, null, values);

        return id;
    }

    public static boolean checkMultipleUsername(String username) {
        playerDbHelper = new PlayerDbHelper(c);
        Db = playerDbHelper.getReadableDatabase();
        selectionArgs[0] = username;

        cursor = Db.query(
                AppContract.Player.TABLE_NAME,   // The table to query
                projectionPass,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                     // The sort order
        );
        cursor.moveToFirst();
        if (cursor.getCount() == 0)
            return false;
        return true;
    }

    public static boolean checkData(String username, String password) {
        playerDbHelper = new PlayerDbHelper(c);
        Db = playerDbHelper.getReadableDatabase();
        selectionArgs[0] = username;

        cursor = Db.query(
                AppContract.Player.TABLE_NAME,   // The table to query
                projectionPass,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                     // The sort order
        );
        cursor.moveToFirst();
        if (cursor.getCount() == 0)
            return false;
        return cursor.getString(1).equals(password);
    }

    public static ArrayList<String> getNames(){
        if(!names.isEmpty())
            names.clear();

        playerDbHelper = new PlayerDbHelper(c);
        Db = playerDbHelper.getReadableDatabase();

        selectionArgs[0] = "1";

        cursor = Db.query(
                AppContract.Player.TABLE_NAME,   // The table to query
                projectionRole,             // The array of columns to return (pass null to get all)
                selectionRole,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                     // The sort order
        );
        cursor.moveToFirst();
        Log.v("err","err");
        do {
            Log.v("err",cursor.getString(1));
                names.add(cursor.getString(1));
                cursor.moveToNext();
        }while (!cursor.isAfterLast());
        return names;
    }

    public static boolean checkRole(String username, int role) {
        playerDbHelper = new PlayerDbHelper(c);
        Db = playerDbHelper.getReadableDatabase();
        selectionArgs[0] = username;

        cursor = Db.query(
                AppContract.Player.TABLE_NAME,   // The table to query
                projectionRole,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                     // The sort order
        );
        cursor.moveToFirst();
        if (cursor.getCount() == 0)
            return false;
        Toast.makeText(c,Integer.toString(cursor.getInt(2)),Toast.LENGTH_SHORT).show();
        return cursor.getInt(2) == role;
    }
}
