package com.example.jinny.expensesnote.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jinny.expensesnote.adapter.ExpenseAdapter;
import com.example.jinny.expensesnote.R;
import com.example.jinny.expensesnote.model.ExpenseModel;
import com.example.jinny.expensesnote.utils.Database;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private RecyclerView rcvExpenses;
    private ExpenseAdapter adapter;
    private List<ExpenseModel> expenseModels;
    private FloatingActionButton fabAdd;
    private FloatingActionButton fabChart;

    public static int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        getDataFromFirebase();
    }

    private void getDataFromFirebase() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("data");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                expenseModels.clear();
                id = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ExpenseModel expenseModel = new ExpenseModel(snapshot.child("note").getValue(String.class),
                            snapshot.child("amount").getValue(Long.class),
                            snapshot.child("id").getValue(Integer.class),
                            snapshot.child("time").getValue(Long.class),
                            snapshot.child("user").getValue(Integer.class));
                    expenseModels.add(expenseModel);
                    adapter.notifyDataSetChanged();
                    Database.getInstance(MainActivity.this).setData(expenseModels);
                    id = Math.max(id, expenseModel.getId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupUI() {
        rcvExpenses = findViewById(R.id.rcv_expenses);
        expenseModels = new ArrayList<>();
        adapter = new ExpenseAdapter(expenseModels, MainActivity.this);
        rcvExpenses.setLayoutManager(new LinearLayoutManager(this));
        rcvExpenses.setAdapter(adapter);

        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(this);
        fabChart = findViewById(R.id.fab_chart);
        fabChart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add: {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                return;
            }
            case R.id.fab_chart: {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                startActivity(intent);
                return;
            }
            default: return;
        }
    }
}
