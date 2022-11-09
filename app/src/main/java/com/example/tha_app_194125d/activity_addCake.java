package com.example.tha_app_194125d;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
//        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);
//        insert = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameTXT = name.getText().toString();
                String descTXT = desc.getText().toString();
                String priceTXT = price.getText().toString();

                Boolean checkinsertdata = DB.inserttha_db(nameTXT, descTXT, priceTXT);
                if(checkinsertdata){
                    Toast.makeText(activity_addCake.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(activity_addCake.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Cursor res = DB.gettha_db();
                if (res.getCount() == 0){
                    Toast.makeText(activity_addCake.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Description :"+res.getString(1)+"\n");
                    buffer.append("Price :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(activity_addCake.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}