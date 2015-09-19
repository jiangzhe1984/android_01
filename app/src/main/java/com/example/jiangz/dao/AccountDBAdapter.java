package com.example.jiangz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.jiangz.entity.Account;
import com.example.jiangz.utils.Kalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 记账操作
 * Created by JiangZ on 2015-09-05.
 */
public class AccountDBAdapter {
    public static final String KEY_ROWID = "id";
    public static final String KEY_PRICE = "price";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_CATEGORYID = "categoryId";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_REMARKS = "remarks";
    public static final String KEY_ACCOUNTTIME = "accountTime";
    public static final String KEY_CURRENTMONTH = "currentMonth";


    private static final String TAG = "AccountDBAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Reminder";
    private static final String SQLITE_TABLE = "account";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    //database create statement
    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_PRICE + "," +
                    KEY_AMOUNT + "," +
                    KEY_CATEGORYID + "," +
                    KEY_CATEGORY + "," +
                    KEY_REMARKS + "," +
                    KEY_CURRENTMONTH + "," +
                    KEY_ACCOUNTTIME + ");";


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }


    public AccountDBAdapter(Context ctx){
        this.mCtx = ctx;
        mDbHelper = new DatabaseHelper(mCtx);
    }

    public AccountDBAdapter open(String action){
        switch (action){
            case "read" : mDb = mDbHelper.getReadableDatabase(); break;
            case "write" : mDb = mDbHelper.getWritableDatabase(); break;
        }
        return this;
    }

    public void closeHelper() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public void closeDB() {
        if (mDb != null && mDb.isOpen()) {
            mDb.close();
        }
    }

    public long createAccount(Account account){

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PRICE,account.getPrice());
        initialValues.put(KEY_CATEGORYID,account.getCategoryId());
        initialValues.put(KEY_CATEGORY,account.getCategory());
        initialValues.put(KEY_REMARKS,account.getRemarks());
        initialValues.put(KEY_ACCOUNTTIME,new SimpleDateFormat("yyyy-MM-dd").format(account.getAccountTime()));
        initialValues.put(KEY_CURRENTMONTH, Kalendar.getCurrentMonth());

        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllAccounts() {
        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public int deleteAccountById(Integer id){
        return  mDb.delete(SQLITE_TABLE, KEY_ROWID + " =" + id, null);
    }

    public int update(Account account){
        ContentValues values = new ContentValues();
        values.put(KEY_PRICE,account.getPrice());
        values.put(KEY_CATEGORYID,account.getCategoryId());
        values.put(KEY_CATEGORY,account.getCategory());
        values.put(KEY_REMARKS,account.getRemarks());
        values.put(KEY_ACCOUNTTIME,new SimpleDateFormat("yyyy-MM-dd").format(account.getAccountTime()));

        String whereClause = "id = ?";

        String[] whereArgs={String.valueOf(account.getId())};

        return mDb.update(SQLITE_TABLE, values, whereClause, whereArgs);
    }

    public List<Account> fetchAccountByMap(Map<Object,Object> map){

        List<Account> accounts = new ArrayList<>();

        String whereClause = " 1=1 ";
        String[] whereArgs = null;

        if(!map.isEmpty()){
            whereArgs = new String[map.size()];
            int foreach = 0;
            for(Map.Entry<Object,Object> entry : map.entrySet()){
                 if(!entry.getValue().equals("")){
                     if(whereClause != null && !whereClause.equals("")){
                         whereClause += " and ";
                     }
                     if(String.valueOf(entry.getKey()).equals(KEY_ROWID)){
                         whereClause += String.valueOf(entry.getKey()) +" = ? ";
                     }else if(String.valueOf(entry.getKey()).equals(KEY_CURRENTMONTH)){
                         whereClause += String.valueOf(entry.getKey()) +" = ? ";
                     }else{
                         whereClause += String.valueOf(entry.getKey()) +" like ? ";
                     }

                     whereArgs[foreach] = String.valueOf(entry.getValue());
                     foreach++;
                 }
            }
        }

        Cursor mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
                        KEY_PRICE, KEY_CATEGORYID, KEY_CATEGORY, KEY_REMARKS, KEY_ACCOUNTTIME, KEY_CURRENTMONTH},
                whereClause, whereArgs,null, null, null, null);

        if (mCursor.moveToFirst()) {
            do{
                Account account = new Account();
                account.setId(mCursor.getInt(0));
                account.setPrice(mCursor.getDouble(1));
                account.setCategoryId(mCursor.getInt(2));
                account.setCategory(mCursor.getString(3));
                account.setRemarks(mCursor.getString(4));
                try {
                    account.setAccountTime(new SimpleDateFormat("yyyy-MM-dd").parse(mCursor.getString(5)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                account.setCurrentMonth(mCursor.getString(6));
                accounts.add(account);
            }while(mCursor.moveToNext());
        }

        if(mCursor != null){
            mCursor.close();
        }

        return accounts;
    }

}
