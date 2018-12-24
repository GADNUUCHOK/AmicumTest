package com.example.work_gadnuuk.amicum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Amicum.db";
    public static final String TABLE_NAME = "Amicum_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SMENA";
    public static final String COL_3 = "MESTO";
    public static final String COL_4 = "ZVENO";
    public static final String COL_5 = "OPERACIYA";
    public static final String COL_6 = "SHAG";
    public static final String COL_7 = "CIKL";
    public static final String COL_8 = "METROV";
    public static final String COL_9 = "STRIPS";
    public static final String COL_10 = "ANKER";
    public static final String COL_11 = "ZATYAGKA";
    public static final String COL_12 = "ANKERBOR";
    public static final String COL_13 = "SETKA";
    public static final String COL_14 = "AMPLUA";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {                                               //Создание бызы данных
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SMENA INTEGER, MESTO TEXT, ZVENO TEXT, OPERACIYA TEXT, SHAG REAL, CIKL INTEGER, METROV INTEGER, STRIPS INTEGER, ANKER INTEGER, ZATYAGKA INTEGER, ANKERBOR INTEGER, SETKA INTEGER, AMPLUA INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean InsertData(String smena, String mesto, String zveno, String operaciya, String shag,    //Наполнение контентом каждого параметра БД
                              String cikl, String metrov, String strips, String anker, String zatyagka,
                              String ankerbor, String setka, String amplua) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,smena);
        contentValues.put(COL_3,mesto);
        contentValues.put(COL_4,zveno);
        contentValues.put(COL_5,operaciya);
        contentValues.put(COL_6,shag);
        contentValues.put(COL_7,cikl);
        contentValues.put(COL_8,metrov);
        contentValues.put(COL_9,strips);
        contentValues.put(COL_10,anker);
        contentValues.put(COL_11,zatyagka);
        contentValues.put(COL_12,ankerbor);
        contentValues.put(COL_13,setka);
        contentValues.put(COL_14,amplua);
        long result = sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {                                                                       //Получить информацию по каждой позиции БД и всех добавленных элементов
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}
