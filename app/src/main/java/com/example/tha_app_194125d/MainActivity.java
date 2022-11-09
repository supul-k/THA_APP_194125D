package com.example.tha_app_194125d;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView receiveTxt, name, desc, price;
    ImageButton IBtn;
    DBHelper DB;
    String message1 = "edit" , message2 = "add";
    private ArrayList CakeDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //clicking Edit button goes to activity_add_cake with extra message "edit"
        Button mButton = findViewById(R.id.buttonEdit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_addCake.class);
                intent.putExtra("message", message1);
                startActivity(intent);
            }
        });

        //clicking Edit button goes to "activity_add_cake" with extra message "add"
        ImageButton iButton = findViewById(R.id.imageButtonAdd);
        iButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_addCake.class);
                intent.putExtra("message", message2);
                startActivity(intent);
            }
        });


        //Define ImageButton
        IBtn = findViewById(R.id.imageButtonView);

        DB = new DBHelper(this);

        //Set Image Button to show all cakes as a alert
        IBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.gettha_db();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Description :" + res.getString(1) + "\n");
                    buffer.append("Price :" + res.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });

        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        price = findViewById(R.id.price);

        CakeDataArrayList = new ArrayList();

        Cursor res = DB.gettha_db();
        if (res.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        int count = 0;
        while (res.moveToNext()){
            if (count == 1){
                name.append(res.getString(0) + "\n");
                desc.append(res.getString(1) + "\n");
                price.append(res.getString(2) + "\n");
            }

            count = count + 1;
        }
    }

}