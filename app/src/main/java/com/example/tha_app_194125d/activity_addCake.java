package com.example.tha_app_194125d;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class activity_addCake extends AppCompatActivity {

    EditText name, desc , price;
    Button insert, update, delete , view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cake);

        name = findViewById(R.id.editTextCakeName);
        desc = findViewById(R.id.editTextCakeDesc);
        price = findViewById(R.id.editTextCakePrice);

        insert = findViewById(R.id.buttonAdd);
        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);

        DB = new DBHelper(this);

        //came from activity "Main Activity" for hide button
        Intent i = getIntent();
        String message = i.getStringExtra("message");

//        update.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//
//                AlertDialog alert = new AlertDialog.Builder(activity_addCake.this).create();
//                alert.setCancelable(true);
//                alert.setTitle("No Internet access");
//                alert.setMessage(message);
//                alert.show();
//            }
//        });

        //hiding buttons
        if (Objects.equals(message, "add")){
            update.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
        }else if (Objects.equals(message, "edit")){
            insert.setVisibility(View.INVISIBLE);
        }

        //Add all the three field to the data and submit to the SQLite database
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameTXT = name.getText().toString();
                String descTXT = desc.getText().toString();
                String priceTXT = price.getText().toString();

                //Validating three fields
                if (nameTXT.isEmpty() || descTXT.isEmpty() || priceTXT.isEmpty()) {
                    Toast.makeText(activity_addCake.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkinsertdata = DB.inserttha_db(nameTXT, descTXT, priceTXT);
                if(checkinsertdata){
                    Toast.makeText(activity_addCake.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(activity_addCake.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}