package com.example.talaatmagdy.education;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abdo on 22/05/2017.
 */

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "points.db" ;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE grades (ID INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , point integer");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE grades");
        onCreate(db);
    }

    public void insertAssignment(String points ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("point",points);
        db.insert("grades",null,contentValues);
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c =  db.rawQuery("SELECT * FROM grades",null);
        return c ;
    }
}