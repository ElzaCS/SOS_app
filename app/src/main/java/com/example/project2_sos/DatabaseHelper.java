package com.example.project2_sos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="SOS.db";
    public static final String TABLE_NAME="Contacts_Table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="MOBILE";

//    public static final String TABLE2_NAME="Personal_Table";
//    public static final String COL_T1="ID";
//    public static final String COL_T2="NAME";
//    public static final String COL_T3="BLOOD";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT, "+COL_3+" TEXT)");
//        db.execSQL("create table "+TABLE2_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_T2+" TEXT, "+COL_T3+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertContact(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2, contact.name);
        contentValues.put(COL_3, contact.mobile);
        long results=db.insert(TABLE_NAME,null, contentValues);
        if(results==-1)
            return false;
        else
            return true;

    }

    public ArrayList<Contact> getAllContacts()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        ArrayList<Contact> allContacts = new ArrayList<>();
        while (res.moveToNext()){
            Contact contact = new Contact();
            contact.name = res.getString(1);
            contact.mobile = res.getString(2);
            allContacts.add(contact);
        }
        return allContacts;
    }
    public Cursor getProductByProductNameLike(String name_regex)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_2 +" LIKE '%"+name_regex+"%'" ,null);
        return res;
    }

    public Cursor getProductByID(Integer productID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_1 +" ='"+productID+"'" ,null);
        return res;
    }

//    public boolean updateInfo(PersonalInfo user)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(COL_2, name);
//        contentValues.put(COL_3, surname);
//        db.update(TABLE_NAME,contentValues,"ID=?",new String[] {id}) ;
//        return true;
//    }

    public void deleteAllData()    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        return;

    }
}
