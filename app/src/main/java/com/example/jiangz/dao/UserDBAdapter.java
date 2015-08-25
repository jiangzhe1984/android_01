package com.example.jiangz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.jiangz.entity.Userinfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBAdapter{
    public static final String KEY_ROWID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ISENABLE = "isenable";
    public static final String KEY_ISLOCK = "islock";

    private static final String TAG = "UserDBAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Reminder";
    private static final String SQLITE_TABLE = "userInfo";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    //database create statement
    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_USERNAME + "," +
                    KEY_PASSWORD + "," +
                    KEY_ISENABLE + "," +
                    KEY_ISLOCK + ");";

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


    public UserDBAdapter(Context ctx){
        this.mCtx = ctx;
        mDbHelper = new DatabaseHelper(mCtx);
    }

    public UserDBAdapter open(String action) throws SQLException {
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

    /**
     * 新增用户
     * @param userinfo
     * @return
     */
    public long createUser(Userinfo userinfo) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, userinfo.getUsername());
        initialValues.put(KEY_PASSWORD, userinfo.getPassword());
        initialValues.put(KEY_ISENABLE, userinfo.getIsEnable());
        initialValues.put(KEY_ISLOCK, userinfo.getIsLock());

        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    /**
     * 删除所有用户
     * @return
     */
    public boolean deleteAllUsers() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    /**
     * 根据ID删除用户
     * @return
     */
    public int deleteUsersById(Integer id){
      return  mDb.delete(SQLITE_TABLE, KEY_ROWID + " =" + id, null);
    }

    /**
     * 修改用户
     * @param userinfo
     * @return
     */
    public int update(Userinfo userinfo){

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, userinfo.getUsername());
        values.put(KEY_PASSWORD, userinfo.getPassword());
        values.put(KEY_ISENABLE, userinfo.getIsEnable());
        values.put(KEY_ISLOCK, userinfo.getIsLock());

        String whereClause = "id = ?";

        String[] whereArgs={String.valueOf(userinfo.getId())};

       return mDb.update(SQLITE_TABLE, values, whereClause, whereArgs);

    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     * @throws SQLException
     */
    public List<Userinfo> fetchUsersByName(String username) throws SQLException {
        Log.w(TAG, username);
        List<Userinfo> userinfos = new ArrayList<>();

        String whereClause = username != null && !username.equals("") ? "username = ?" : null;

        String[] whereArgs = username != null && !username.equals("") ? new String[]{username} : null;

        Cursor mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
                            KEY_USERNAME, KEY_PASSWORD, KEY_ISENABLE, KEY_ISLOCK},
                whereClause, whereArgs,null, null, null, null);

        if (mCursor.moveToFirst()) {
            do{
                Userinfo userinfo = new Userinfo();
                userinfo.setId(mCursor.getInt(0));
                userinfo.setUsername(mCursor.getString(1));
                userinfo.setPassword(mCursor.getString(2));
                userinfo.setIsEnable(mCursor.getInt(3));
                userinfo.setIsLock(mCursor.getInt(4));
                userinfos.add(userinfo);
            }while(mCursor.moveToNext());
        }

        if(mCursor != null){
             mCursor.close();
        }

        return userinfos;

    }

    /**
     * 返回所有用户
     * @return
     */
    public List<Userinfo> fetchAllUsers() {

        List<Userinfo> userinfos = new ArrayList<>();

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                        KEY_USERNAME, KEY_PASSWORD, KEY_ISENABLE, KEY_ISLOCK},
                null, null, null, null, null);

        if ( mCursor.moveToFirst()) {
            do{
                Userinfo userinfo = new Userinfo();
                userinfo.setId(mCursor.getInt(0));
                userinfo.setUsername(mCursor.getString(1));
                userinfo.setPassword(mCursor.getString(2));
                userinfo.setIsEnable(mCursor.getInt(3));
                userinfo.setIsLock(mCursor.getInt(4));
                userinfos.add(userinfo);
            }while(mCursor.moveToNext());
        }

        if(mCursor != null){
            mCursor.close();
        }

        return userinfos;
    }

    /**
     * 根据用户名和密码验证用户
     * @param username
     * @param password
     * @return
     */
    public Userinfo validateUser(String username,String password){

        String[] whereArgs={username,password};

        Cursor cursor = mDb.query(SQLITE_TABLE,new String[] {KEY_ROWID,
                KEY_USERNAME, KEY_PASSWORD, KEY_ISENABLE, KEY_ISLOCK}, KEY_USERNAME + " = ? and " + KEY_PASSWORD + " = ?",whereArgs,null, null, null, null);

       if(cursor.moveToFirst()){

          Userinfo userinfo = new Userinfo();
           userinfo.setId(cursor.getInt(0));
           userinfo.setUsername(cursor.getString(1));
           userinfo.setPassword(cursor.getString(2));
           userinfo.setIsEnable(cursor.getInt(3));
           userinfo.setIsLock(cursor.getInt(4));

           return userinfo;
       }

        if(cursor != null){
            cursor.close();
        }

        return null;
    }


}