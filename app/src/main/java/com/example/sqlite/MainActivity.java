package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnStore, btnGetAll;
    private EditText editTextName;
    private DatabaseHelper databaseHelper;
    private TextView textViewNames;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        btnStore = (Button) findViewById(R.id.btn_store);
        btnGetAll = (Button) findViewById(R.id.btn_getAll);
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        textViewNames = (TextView) findViewById(R.id.text_view_names);


        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHelper.addTree(editTextName.getText().toString());
                editTextName.setText("");
                Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();

            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList = databaseHelper.getAllTrees();
                textViewNames.setText("");

                for (int i = 0; i < arrayList.size(); i++){

                    textViewNames.setText(textViewNames.getText().toString() + arrayList.get(i) + "\n");

                }

            }
        });

    }
}
