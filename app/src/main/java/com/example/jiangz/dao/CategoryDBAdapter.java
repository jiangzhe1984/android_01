package com.example.jiangz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.jiangz.entity.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类别操作
 * Created by JiangZ on 2015-09-05.
 */
public class CategoryDBAdapter {
    public static final String KEY_ROWID = "id";
    public static final String KEY_USERNAME = "name";

    private static final String TAG = "CategoryDBAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Reminder";
    private static final String SQLITE_TABLE = "category";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    //database create statement
    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_USERNAME +");";

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

    public CategoryDBAdapter(Context ctx){
        this.mCtx = ctx;
        mDbHelper = new DatabaseHelper(mCtx);
    }

    public CategoryDBAdapter open(String action){
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

    public long createCategory(Category category){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME,category.getName());
        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllCategorys() {
        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public int deleteCategoryById(Integer id){
        return  mDb.delete(SQLITE_TABLE, KEY_ROWID + " =" + id, null);
    }

    public int update(Category category){
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME,category.getName());

        String whereClause = "id = ?";

        String[] whereArgs={String.valueOf(category.getId())};

        return mDb.update(SQLITE_TABLE, values, whereClause, whereArgs);
    }

    public List<Category> fetchCategoryByMap(Map<Object,Object> map){

        List<Category> categories = new ArrayList<>();

        String whereClause = null;
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
                    }else{
                        whereClause += String.valueOf(entry.getKey()) +" like ? ";
                    }

                    whereArgs[foreach] = String.valueOf(entry.getValue());
                    foreach++;
                }
            }
        }

        Cursor mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,KEY_USERNAME},
                whereClause, whereArgs,null, null, null, null);

        if (mCursor.moveToFirst()) {
            do{
                Category category = new Category();
                category.setId(mCursor.getInt(0));
                category.setName(mCursor.getString(1));
                categories.add(category);
            }while(mCursor.moveToNext());
        }

        if(mCursor != null){
            mCursor.close();
        }

        return categories;
    }

}
