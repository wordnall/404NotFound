package ssu.groupname.baseapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHandler extends SQLiteOpenHelper {
    //information of data base
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cookDataBase.db";
    public static final String TABLE_NAME = "ingredient";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_EXP = "EXP";

    //init the data base
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_NAME + "EXP DATE," + COLUMN_EXP + "TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){}
    public String loadHandler()
    {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext())
        {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0)+" "+ result_1+ System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(INGdatabase ING) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXP, ING.getEXPdate());
        values.put(COLUMN_NAME, ING.getIngredent());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    public INGdatabase findHandler(String ingredient){
        String query = "Select * From " + TABLE_NAME + "Where" + COLUMN_NAME + " = " + "'" + ingredient + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        INGdatabase ingdatabase = new INGdatabase();
        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            ingdatabase.setEXPdate(cursor.getString(0));
            ingdatabase.setIngredent(cursor.getString(1));
            cursor.close();
        }else
        {
            ingdatabase = null;
        }
        db.close();
        return ingdatabase;
    }
    public boolean deleteHandler(String ingredient){
        boolean result = false;
        String query = "Select * From " + TABLE_NAME + "where" + COLUMN_EXP + "= '" + ingredient + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        INGdatabase ingdatabase = new INGdatabase();
        if (cursor.moveToFirst())
        {
            ingdatabase.setIngredent(cursor.getString(1));
            db.delete(TABLE_NAME,COLUMN_NAME + "=?", new String[])
        }


        return false;
    }
    public boolean updateHandler(String ingredient, String EXP){
        return false;
    }

}