package com.example.cardiocareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ConsultantActivity extends AppCompatActivity {

    List<Consultant> consultantList;
    SQLiteDatabase mDatabase;
    ListView listViewConsultants;
    ConsultantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant);

        listViewConsultants = findViewById(R.id.listViewConsultants);
        consultantList = new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        //this method will display the employees in the list
        showConsultantsFromDatabase();
    }

    private void showConsultantsFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorConsultants = mDatabase.rawQuery("SELECT * FROM consultant", null);

        //if the cursor has some data
        if (cursorConsultants.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                consultantList.add(new Consultant(
                        cursorConsultants.getInt(0),
                        cursorConsultants.getString(1),
                        cursorConsultants.getString(2),
                        cursorConsultants.getString(3),
                        cursorConsultants.getString(4),
                        cursorConsultants.getString(5),
                        cursorConsultants.getString(6),
                        cursorConsultants.getString(7),
                        cursorConsultants.getString(8),
                        cursorConsultants.getString(9),
                        cursorConsultants.getInt(10),
                        cursorConsultants.getInt(11),
                        cursorConsultants.getInt(12),
                        cursorConsultants.getInt(13)
                ));
            } while (cursorConsultants.moveToNext());
        }
        //closing the cursor
        cursorConsultants.close();

        //creating the adapter object
        adapter = new ConsultantAdapter(this, R.layout.list_layout_consultant, consultantList, mDatabase);

        //adding the adapter to listview
        listViewConsultants.setAdapter(adapter);
    }

}