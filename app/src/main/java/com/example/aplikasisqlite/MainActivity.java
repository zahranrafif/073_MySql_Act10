package com.example.aplikasisqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplikasisqlite.adapter.TemanAdapter;
import com.example.aplikasisqlite.database.DBController;
import com.example.aplikasisqlite.database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nm,tlp;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView2);
        fab = findViewById(R.id.floatingBtn);
        bacadata();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemanBaru.class);
                startActivity(intent);
            }
        });
    }
    public void bacadata(){
        ArrayList<HashMap<String,String>> daftarteman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();
        //memindah hasil dari query kedalam teman
        for(int i=0; i<daftarteman.size(); i++){
            Teman teman = new Teman();
            teman.setId(daftarteman.get(i).get("id").toString());
            teman.setNama(daftarteman.get(i).get("nama").toString());
            teman.setTelpon(daftarteman.get(i).get("telpon").toString());
            //pindahkan dari Teman kedalam ArrayList teman di adapter
            temanArrayList.add(teman);
        }
    }
}