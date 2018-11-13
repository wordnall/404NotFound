package ssu.groupname.baseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ingredientDB.db";
    public static final String TABLE_INGREDIENTS = "ingredient";
    public static final String COLUMN_ID = "IngredentID";
    public static final String COLUMN_NAME = "IngredientName";
    public static final String COLUMN_EXP = "IngredientEXP";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_INGREDIENTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT, " + COLUMN_EXP + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
        //create table ingredient(IngredientID integer primary key, IngredientName text, IngredientEXP text)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);

    }

    public void addHandler(Ingredient ingredient) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, ingredient.getID());
        values.put(COLUMN_NAME, ingredient.getIngredientName());
        values.put(COLUMN_EXP, ingredient.getEXPdate());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_INGREDIENTS, null, values);
        db.close();
    }

    public Ingredient findHandler(String ingredientname) {
        String query = "Select * FROM " + TABLE_INGREDIENTS + " WHERE " +
                COLUMN_NAME + " = '" + ingredientname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Ingredient ingredient = new Ingredient();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            ingredient.setID(Integer.parseInt(cursor.getString(0)));
            ingredient.setIngredientName(cursor.getString(1));
            ingredient.setEXPdate(cursor.getString(2));
            cursor.close();
        } else {
            ingredient = null;
        }
        db.close();
        return ingredient;
    }

    public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_INGREDIENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 + " " +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Ingredient ingredient = new Ingredient();
        if (cursor.moveToFirst()) {
            ingredient.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_INGREDIENTS, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(ingredient.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int ID, String name,String EXP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_EXP, EXP);
        return db.update(TABLE_INGREDIENTS, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}