package com.example.projectv4.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.projectv4.data.AppContract.Player;

public class PlayerDbHelper extends SQLiteOpenHelper {
    public static final int DATA_BASE_VERSION = 1;
    public static final String DATA_BASE_NAME = "players.db";
    public static final String TEXT_TYPE =" TEXT";
    public static final String INT_TYPE =" NUMBER(2)";
    public static final String FLOAT_TYPE =" NUMBER(3,1)";
    public static final String COMMA = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Player.TABLE_NAME + " (" +
                    Player._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +COMMA+
                    Player.COLUMN_NAME + TEXT_TYPE + COMMA+
                    Player.COLUMN_PASSWORD + TEXT_TYPE + COMMA+
                    Player.COLUMN_AGE + INT_TYPE+COMMA+
                    Player.COLUMN_GENDER + INT_TYPE+COMMA+
                    Player.COLUMN_ROLE + INT_TYPE+COMMA+
                    Player.COLUMN_WEIGHT +FLOAT_TYPE+COMMA+
                    Player.COLUMN_HEIGHT +FLOAT_TYPE+")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Player.TABLE_NAME;


    public PlayerDbHelper(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
