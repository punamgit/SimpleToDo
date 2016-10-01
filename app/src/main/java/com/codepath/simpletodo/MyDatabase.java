package com.codepath.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Punam on 9/27/2016.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyApplication";
    public static final int VERSION = 1;
}
