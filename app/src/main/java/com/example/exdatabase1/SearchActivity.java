package com.example.exdatabase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        List<String> usersList = new ArrayList();
        Intent i1=getIntent();
        String name= i1.getStringExtra("name");
        String[] columns = { DatabaseContract.Users._ID, DatabaseContract.Users.COL_FULLNAME, DatabaseContract.Users.COL_EMAIL };
        String sortOrder = DatabaseContract.Users.COL_FULLNAME + " ASC";
        Cursor c = db.query(DatabaseContract.Users.TABLE_NAME, null, null, null, null, null, sortOrder);

        while (c.moveToNext()) {

            Long id = c.getLong(0);
            String name1 = c.getString(1);
            String Email = c.getString(2);
            usersList.add(id + "-" + name1 + "(" + Email + ")");
        }
        ListView lv=(ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, usersList);

        lv.setAdapter(adapter);

        c.close();
        // db.close();
    }
}
