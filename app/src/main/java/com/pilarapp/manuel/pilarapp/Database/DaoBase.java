package com.pilarapp.manuel.pilarapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Manuel on 30/09/2015.
 */
public abstract class DaoBase{

    // First base version
    // Update this attribute if version change
    protected final static int VERSION = 1;

    // database name
    protected final static String NAME = "infor.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    protected String query;
    protected String[] args;
    protected Cursor c;

    public DaoBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NAME, null, VERSION);
    }

    /**
     * Open the database to read and write
     *
     * @return
     */
    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    /**
     * Close the database
     */
    public void close() {
        mDb.close();
    }

    /**
     * This method returns the database
     *
     * @return
     */
    public SQLiteDatabase getDb() {
        return mDb;
    }
}

