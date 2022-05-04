package com.example.projectv4.data;

import android.provider.BaseColumns;

public class AppContract implements BaseColumns {
    private AppContract(){};
    public static class Player implements BaseColumns {
        public static final String TABLE_NAME = "players";
        public static final String _ID = BaseColumns._ID ;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_ROLE = "role";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_HEIGHT = "height";
        public static final int MALE = 1;
        public static final int FEMALE = 2;
        public static final int UNKNOWN = 0;
        public static final int ATHLETE = 1;
        public static final int COACH = 2;

    }
}
