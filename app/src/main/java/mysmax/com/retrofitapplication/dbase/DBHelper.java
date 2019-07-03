package mysmax.com.retrofitapplication.dbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Relational Database
    // SCHEMA

    // CRUD
    private static final String DATABASE_NAME = "RETRODB.db";
    private static final String DATABASE_TABLE_NAME = "RetroData";

    // 1
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    // 2
    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table " + DATABASE_TABLE_NAME +" (_id integer primary key AutoIncrement,name text, address text,designation text, salary number)");
    }

    // 3
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DATABASE_NAME);
        onCreate(db); // Recreate During any Changes done to Database Table structure
    }

    // DML
    // Manipulation
    public void createRetroValue(String name, String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("address",address);
        values.put("designation","clerk");
        values.put("salary",5000);

        db.insert(DATABASE_TABLE_NAME,null,values);
        db.close();
    }

    public Cursor readRetroAllValues()
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        String[] query = {"select * from " + DATABASE_TABLE_NAME};

        return db.rawQuery(null,query);
    }

    private void printRetroValue()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // create Cursor in order to parse our sqlite results
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_NAME, null);
        // if Cursor is contains results
        if (cursor != null) {
            // move cursor to first row
            if (cursor.moveToFirst()) {
                do {
                    // Get version from Cursor
                    String empName = cursor.getString(cursor.getColumnIndex("name"));
                    String empDesig = cursor.getString(cursor.getColumnIndex("designation"));
                    //arrayList.add(empObject);

                } while (cursor.moveToNext()); // move to next row
            }
        }
    }

    public Cursor readRetroValueForName(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = {"select * from " + DATABASE_TABLE_NAME + " where name="+name};

        return db.rawQuery(null,query);
    }

    public void updateRetroValue(String id,String updateValue)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("address",updateValue);

        db.update(DATABASE_TABLE_NAME,value,"_id="+id,null);
    }

    public void deleteRetroValue(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = {id};

        db.delete(DATABASE_TABLE_NAME,null,query);
    }

}
