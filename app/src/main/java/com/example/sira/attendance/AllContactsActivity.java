package com.example.sira.attendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class AllContactsActivity extends Activity {
    public final static int ACT_SHOW = 100;
    public final static String TAG = "AllContactsActivity";


    ListView lvContacts = null;
    ContactDBHelper helper;
    Cursor cursor;
    MyCursorAdapter adapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        helper = new ContactDBHelper(this);
        db = helper.getReadableDatabase();

        //cursor = db.rawQuery("select * from " + ContactDBHelper.TABLE_NAME, null);

        lvContacts = (ListView)findViewById(R.id.lvContacts);
        adapter = new MyCursorAdapter(this, cursor);
        lvContacts.setAdapter(adapter);

       // 참고: http://creaby.tistory.com/8
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                db = helper.getReadableDatabase();
                Intent intent = new Intent(AllContactsActivity.this, DetailContactActivity.class);
                cursor = (Cursor)adapter.getItem(pos);
                String index = cursor.getString(cursor.getColumnIndex("_id"));
                int id = Integer.parseInt(index);

                Log.i(TAG, String.valueOf(id));

                intent.putExtra("id", id);
                startActivityForResult(intent, ACT_SHOW);
            }
        });

        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(AllContactsActivity.this);
                builder.setTitle("항목 삭제");
                builder.setMessage("항목을 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = helper.getWritableDatabase();
                        cursor = (Cursor)adapter.getItem(pos);
                        String index = cursor.getString(cursor.getColumnIndex("_id"));
                        int id = Integer.parseInt(index);

                        db.execSQL("delete from " + ContactDBHelper.TABLE_NAME + " where _id = '"+id+"';");
                        onResume();
                        //adapter.changeCursor(cursor);
                        //helper.close();
                        //finish();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialog dlg = builder.create();
                dlg.show();
                return true;//onClick과 onLongClick동시 구현할 때 (true: 이벤트 완료)설정해야 클릭 처리 안됨
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
//        DB에서 데이터를 읽어와 Adapter에 설정
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + ContactDBHelper.TABLE_NAME, null);
//cursor가 바뀌었으니 새로운 정보가 보여지게 된다. notify를 시켜주지 않아도 바뀐 부분만 바꿔치기(?)
        //cursorAdapter에 들어가 있는 cursor는 close 시키면 안된다. close 시키면 커서가 날라감-정보가 날라감
        //나중에 관리할 때 cursor close 시키는데 destroy 시점에 close 시킨다.
        //swapCursor() : 커서를 바꿔치기하고 close하지 않고 보존한다. 다시 swap하면 이전의 내용 다시 swap, 바뀐 내용으로 화면을 보여주다가 다시 전체 내용, 이전의 내용을 다시 보여준다.
        //ChangCursor() : 현재 어댑터에 설정되어 있는 cursor를 close한 후 매개변수 cursor로 adapter 재설정
        adapter.changeCursor(cursor); //cursor가 바뀌었으니 새로운 정보가 보여지게 된다. notify를 시켜주지 않아도 바뀐 부분만 바꿔치기(?)
        helper.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cursor 사용 종료
        if (cursor != null) cursor.close();
    }

}
