package com.example.sira.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //20150970 김시라

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        Intent intent = null;
        switch(v.getId()){
            case R.id.btn_all:
                intent = new Intent(this, AllContactsActivity.class);
                break;
            case R.id.btn_insert:
                intent = new Intent(this, InsertContactActivity.class);
                break;
        }

        if (intent != null) startActivity(intent);
    }


}
