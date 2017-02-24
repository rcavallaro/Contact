package com.rickcavallaro.contact.database;

/*
 * Created by Rick on 2/20/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.rickcavallaro.contact.Contact;
import com.rickcavallaro.contact.database.ContactDbSchema;


public class ContactBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "contactBase.db";

    public ContactBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + ContactDbSchema.ContactTable.NAME + "( " +
                "_id integer primary key autoincrement, " +
                ContactDbSchema.ContactTable.Cols.UUID + ", " +
                ContactDbSchema.ContactTable.Cols.NAME + ", " +
                ContactDbSchema.ContactTable.Cols.EMAIL + ", " +
                ContactDbSchema.ContactTable.Cols.FAVORITE + ", " +
                ContactDbSchema.ContactTable.Cols.ADDRESS + ", " +
                ContactDbSchema.ContactTable.Cols.IMAGE + ", " +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
