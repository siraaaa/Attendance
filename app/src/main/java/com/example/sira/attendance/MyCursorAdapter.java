package com.example.sira.attendance;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyCursorAdapter extends CursorAdapter {

    LayoutInflater inflater;
    Cursor cursor;

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //layout = R.layout.list_view_layout;
        cursor = c;
    }

  /*  @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }*/

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //ListView 내부에 표시할 뷰에 cursor의 데이터 연결
        TextView tvContactMajor = (TextView)view.findViewById(R.id.tvContactMajor);
        TextView tvContactName = (TextView)view.findViewById(R.id.tvContactName);
        TextView tvContactNumber = (TextView)view.findViewById(R.id.tvContactNumber);

        tvContactMajor.setText(cursor.getString(1));
        tvContactName.setText(cursor.getString(2));
        tvContactNumber.setText(cursor.getString(3));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // inflator를 사용하여 ListView 내부에 표시할 view를 inflation
        //inflater = LayoutInflater.from(context);
        View listItemLayout = inflater.inflate(R.layout.list_view_layout, parent, false);
        return listItemLayout;
    }

}
