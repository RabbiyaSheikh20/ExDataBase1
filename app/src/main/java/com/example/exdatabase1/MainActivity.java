package com.example.exdatabase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.exdatabase1.DatabaseContract.Users.TABLE_NAME;
//import static com.example.ExDataBase1.DatabaseContract.Users.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText et1, et2;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
    }
    public void InsertData(View v) {
        db = dbHelper.getWritableDatabase();

        et1 = (EditText) findViewById(R.id.ed1);
        et2 = (EditText) findViewById(R.id.ed2);
        String val1 = et1.getText().toString();
        String val2 = et2.getText().toString();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Users.COL_FULLNAME, val1);
        values.put(DatabaseContract.Users.COL_EMAIL, val2);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId > 0) {
            Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
        }

        db.close();

    }
    public void ListRecords(View v)
    {
        Intent i1=new Intent(this,SearchActivity.class);
        startActivity(i1);
    }
    public void UpdateRecords(View v)
    {
        db = dbHelper.getWritableDatabase();

        et1 = (EditText) findViewById(R.id.ed1);
        String val1 = et1.getText().toString();
        et2 = (EditText) findViewById(R.id.ed2);
        String val2=et2.getText().toString();
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.Users.COL_EMAIL,val2);
        String[] wherearg={val1};
        Integer count= db.update(TABLE_NAME, args, DatabaseContract.Users.COL_FULLNAME + "=?",wherearg);
        if (count > 0) {
            Toast.makeText(this, count+"  Records updated: " , Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public void SearchRecords(View v)
    {
        db = dbHelper.getWritableDatabase();

        et1 = (EditText) findViewById(R.id.ed1);
        et2 = (EditText) findViewById(R.id.ed2);
        String val1 = et1.getText().toString();
        String[] columns={DatabaseContract.Users._ID, DatabaseContract.Users.COL_FULLNAME, DatabaseContract.Users.COL_EMAIL};
        Cursor cursor = db.query(TABLE_NAME, columns, DatabaseContract.Users.COL_FULLNAME + "=?",
                new String[] { val1}, null, null, null, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            String email = cursor.getString(2);
            et2.setText(email);
        }
        else
            et2.setText("No record found");

        db.close();

    }
    public void DeleteRecords(View v)
    {
        db = dbHelper.getWritableDatabase();

        et1 = (EditText) findViewById(R.id.ed1);
        String val1 = et1.getText().toString();
        Integer i1= db.delete(TABLE_NAME, "fullname= ?",new String[] {val1});
        if (i1 > 0) {
            Toast.makeText(this, i1+"  Records deleted: " , Toast.LENGTH_SHORT).show();
        }
        db.close();

    }
}
