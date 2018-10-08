package com.example.sira.attendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ContactDBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "contact_db";
    public final static String TABLE_NAME = "contact_table";
    public final static String COL_NAME = "name";
    public final static String COL_PHONE = "phone";
    //public final static String COL_CATEGORY = "category";
    public final static String COL_STUDENT_NUM = "studentNum";
    public final static String COL_MAJOR = "major";
    public final static String COL_SUBJECT1 = "subject1";
    public final static String COL_SUBJECT2 = "subject2";


    public ContactDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( _id integer primary key autoincrement,"
                + COL_MAJOR + " TEXT, " + COL_NAME + " TEXT, " + COL_STUDENT_NUM + " TEXT, " + COL_PHONE + " TEXT, " + COL_SUBJECT1 + " TEXT, " + COL_SUBJECT2 + " TEXT);");

//		샘플 데이터
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '컴퓨터학과', '수지', '20150001', '010-1111-1111', '모바일 응용', '데이터베이스');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '정보통계학과', '설현', '20150002', '010-2222-2222', '데이터베이스 응용', '미적분학');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '사회학과', '동주', '20150003', '010-3333-3333', '사회학개론', '사회발전론');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '법학과', '성률', '20150004', '010-4444-4444', '사회통계', '법사회학');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }
}
