package com.example.sira.attendance;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

public class InsertContactActivity extends Activity {

    ContactDBHelper helper;
    EditText etMajor;
    EditText etName;
    EditText etStudentNum;
    EditText etPhone;
    EditText etSubject1;
    EditText etSubject2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        helper = new ContactDBHelper(this);

        etMajor = (EditText)findViewById(R.id.etMajor);
        etName = (EditText)findViewById(R.id.etName);
        etStudentNum = (EditText)findViewById(R.id.etStudentNum);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etSubject1 = (EditText)findViewById(R.id.etSubject1);
        etSubject2 = (EditText)findViewById(R.id.etSubject2);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_insert:
                SQLiteDatabase db = helper.getWritableDatabase();

                String major = etMajor.getText().toString();
                String name = etName.getText().toString();
                String stuNum = etStudentNum.getText().toString();
                String phone = etPhone.getText().toString();
                String subject1 = etSubject1.getText().toString();
                String subject2 = etSubject2.getText().toString();

//			SQL query를 직접 사용할 경우
                db.execSQL("insert into " + ContactDBHelper.TABLE_NAME + " values(null, '"+major+"','"+name+"','"+stuNum+"','"+phone+"','"+subject1+"','"+subject2+"');");
                helper.close();
                finish();
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
