package com.example.sira.attendance;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailContactActivity extends Activity {

    public final static String TAG = "DetailContactActivity";
    ContactDBHelper helper;
    Cursor cursor;
    SQLiteDatabase db;

    TextView tvMajor;
    TextView tvName;
    TextView tvStuNum;
    TextView tvPhone;
    TextView tvSubject1;
    TextView tvSubject2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        helper = new ContactDBHelper(this);

        tvMajor = (TextView) findViewById(R.id.tvMajor);
        tvName = (TextView) findViewById(R.id.tvName);
        tvStuNum = (TextView) findViewById(R.id.tvStuNum);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvSubject1 = (TextView) findViewById(R.id.tvSubject1);
        tvSubject2 = (TextView) findViewById(R.id.tvSubject2);

        Intent receivedIntent = getIntent();

        //String id = receivedIntent.getStringExtra("id");
        int id=receivedIntent.getIntExtra("id",0);
        Log.d(TAG, String.valueOf(id));
        db = helper.getReadableDatabase();

        cursor = db.rawQuery("select * from " + ContactDBHelper.TABLE_NAME +" where _id = '"+id+"';", null);
        cursor.moveToFirst();
        tvMajor.setText(cursor.getString(1));
        tvName.setText(cursor.getString(2));
        tvStuNum.setText(cursor.getString(3));
        tvPhone.setText(cursor.getString(4));
        tvSubject1.setText(cursor.getString(5));
        tvSubject2.setText(cursor.getString(6));
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
