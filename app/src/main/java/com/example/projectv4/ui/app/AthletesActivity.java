package com.example.projectv4.ui.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectv4.R;
import com.example.projectv4.controller.Controller;
import com.example.projectv4.ui.LoginActivity;

import java.util.ArrayList;

public class AthletesActivity extends AppCompatActivity {
    ListView listView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athletes);
        listView = (ListView) findViewById(R.id.lv);
        Controller.setContext(this);
        ArrayList<String> Athletes = Controller.getNames() ;

       AthletesAdapter AthletesArrayAdapter= new AthletesAdapter(this,Athletes);
       listView.setAdapter(AthletesArrayAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //Toast.makeText(AthletesActivity.this,"err",Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(AthletesActivity.this, MainActivity.class);
              startActivity(intent);
          }
      });
    }
}
