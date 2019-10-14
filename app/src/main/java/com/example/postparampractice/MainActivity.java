package com.example.postparampractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements GetAsyncData.IData {

    Button b2;
    LinkedList<String> finalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetAsyncData(MainActivity.this).execute("http://dev.theappsdr.com/apis/photos/keywords.php");
            }
        });
    }

    public void handleData(LinkedList<String> data){


    }

    @Override
    public void handleLinkedData(LinkedList<String> data) {
        this.finalData = data;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Options")
                .setItems(data.toArray(new CharSequence[data.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}
