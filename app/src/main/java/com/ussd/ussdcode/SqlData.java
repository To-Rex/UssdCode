package com.ussd.ussdcode;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SqlData extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "Student_Table";
    public static final String Col_0 = "ID";
    public static final String Col_1 = "PRODUCT_NAME";
    public static final String Col_2 = "PRODUCT_PRICE";
    public static final String Col_3 = "CATEGORY_SELLECTED";
    public static final String Col_4 = "PROJEKT_DATA";
    public static final String Col_5 = "NOTES";
    public static final String Col_6 = "CHECKB";
    public static final String Col_7 = "PRODUCT";
    public SqlData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCT_NAME TEXT,PRODUCT_PRICE TEXT,CATEGORY_SELLECTED TEXT,PROJEKT_DATA TEXT,NOTES TEXT,CHECKB TEXT,PRODUCT TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
    public Cursor ReadSql() {
        SQLiteDatabase db1 = this.getWritableDatabase();
        return db1.rawQuery("Select * from " + TABLE_NAME + " ORDER BY ID DESC LIMIT 1", null);
    }
    public Cursor ReadSql2() {
        SQLiteDatabase db1 = this.getWritableDatabase();
        return db1.rawQuery("Select * from " + TABLE_NAME, null);
    }
    public Boolean SqlUpdate(String id,String prName, String prprice,String ctSellect, String prData,
                             String notes, String cheskb, String product) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, prName);
        contentValues.put(Col_2, prprice);
        contentValues.put(Col_3, ctSellect);
        contentValues.put(Col_4, prData);
        contentValues.put(Col_5, notes);
        contentValues.put(Col_6, cheskb);
        contentValues.put(Col_7, product);
        int result = sqLiteDatabase.update(TABLE_NAME, contentValues, "ID =?", new String[]{id});
        return result > 0;
    }
    public Boolean SqlInsert(String prName, String prprice,String ctSellect, String prData,
                             String notes, String cheskb, String product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, prName);
        contentValues.put(Col_2, prprice);
        contentValues.put(Col_3, ctSellect);
        contentValues.put(Col_4, prData);
        contentValues.put(Col_5, notes);
        contentValues.put(Col_6, cheskb);
        contentValues.put(Col_7, product);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }
    public void DeleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID =?", new String[]{id});
        db.close();
    }
}