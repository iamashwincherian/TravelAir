package com.example.travelair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_TABLE = "travelAir";
    public static final String TABLE_NAME_USERS=("users_table");
    public static final String TABLE_MY_TRIPS=("trips_table");
    public static final String COL_1=("id");
    public static final String COL_2=("name");
    public static final String COL_3=("age");
    public static final String COL_4=("email");
    public static final String COL_5=("pass");
    public static final String COL_6=("phone");
    public static final String COL_7=("accID");


    public DBHelper(Context context) {
        super(context, DATABASE_TABLE, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME_USERS +" (id integer primary key autoincrement, name text, age text, email text, pass text, phone text, accID text)");
        db.execSQL("create table "+ TABLE_MY_TRIPS +" (id integer primary key autoincrement, flight_name text, flight_from text, flight_to text, flight_date text, flight_time text, flight_seat1 text, flight_seat2 text, flight_seat3 text, flight_seat4 text, flight_class text, user_id text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String name,String age,String email,String pass,String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,pass);
        contentValues.put(COL_6,phone);
        db.insert(TABLE_NAME_USERS,null,contentValues);
        return true;
    }

    public boolean insertTrips(String id,String name,String from,String to,String date, String time, String seat1,String seat2,String seat3,String seat4, String cclass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_id",id);
        contentValues.put("flight_name",name);
        contentValues.put("flight_from",from);
        contentValues.put("flight_to",to);
        contentValues.put("flight_date",date);
        contentValues.put("flight_time",time);
        contentValues.put("flight_seat1",seat1);
        contentValues.put("flight_seat2",seat2);
        contentValues.put("flight_seat3",seat3);
        contentValues.put("flight_seat4",seat4);
        contentValues.put("flight_class",cclass);
        db.insert(TABLE_MY_TRIPS,null,contentValues);
        return true;
    }


    public boolean insertGoogleAcc(String name,String age,String email,String pass,String phone,String accID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,pass);
        contentValues.put(COL_6,phone);
        contentValues.put(COL_7,accID);
        db.insert(TABLE_NAME_USERS,null,contentValues);
        return true;
    }

    public boolean auth(String email,String pass)
    {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from users_table where email=? and pass=?",new String[]{email,pass});
        if(cur.getCount()>=1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME_USERS,null);
        return res;
    }

    public Cursor setUserData(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME_USERS + " where email=?",new String[]{email});
        return res;
    }

    public boolean check(String id){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from users_table where accID=?" ,new String[]{id});
        if(cur.getCount()>=1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getTrip(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_MY_TRIPS + " where user_id=?",new String[]{id});
        return res;
    }

}
