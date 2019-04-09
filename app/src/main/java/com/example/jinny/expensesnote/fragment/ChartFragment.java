package com.example.jinny.expensesnote.fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinny.expensesnote.R;
import com.example.jinny.expensesnote.model.ExpenseModel;
import com.example.jinny.expensesnote.utils.Database;
import com.example.jinny.expensesnote.utils.Utils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private boolean sex;
    private PieChart pieChart;
    private RecyclerView pieLegend;

    private List<ExpenseModel> expenseModels;
    private Map<String, Long> dataMap;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sex = getArguments().getBoolean("sex");
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pieChart = view.findViewById(R.id.pie_chart);
        pieLegend = view.findViewById(R.id.pie_legend);
        setupPieData();
    }

    private void setupPieData() {
        expenseModels = Database.getInstance(getContext()).getData();

        dataMap = new HashMap<>();
        long current;
        float total = 0;
        List<String> notes = new ArrayList<>();
        for (ExpenseModel expenseModel : expenseModels) {
            if (sex && expenseModel.getUser() == 0) continue;
            if (!sex && expenseModel.getUser() == 1) continue;
            current = 0;
            total += expenseModel.getAmount();
            if (dataMap.get(expenseModel.getNote()) != null) {
                current = dataMap.get(expenseModel.getNote());
            } else notes.add(expenseModel.getNote());
            current += expenseModel.getAmount();
            dataMap.put(expenseModel.getNote(), current);
        }

        List<PieEntry> entries = new ArrayList<>();
        for (String s : notes) {
            long amount = dataMap.get(s);
            entries.add(new PieEntry(amount/total, s));
        }
        PieDataSet set = new PieDataSet(entries, "Static");
        int[] colors = new int[] {R.color.GreenYellow, R.color.greenButton, R.color.BlueViolet,
                R.color.Brown, R.color.Yellow, R.color.Beige,
                R.color.Bisque, R.color.Red, R.color.Cyan,
                R.color.Olive, R.color.Orange, R.color.Blue,
                R.color.Coral, R.color.Crimson, R.color.Purple};
        set.setColors(colors , getContext());
        PieData data = new PieData(set);
        data.setDrawValues(false);
        pieChart.setData(data);
        pieChart.setDrawEntryLabels(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setCenterTextSize(18f);
        pieChart.setCenterTextColor(R.color.textBlack);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                pieChart.setCenterText(((PieEntry)e).getLabel());
            }

            @Override
            public void onNothingSelected() {

            }
        });
        pieChart.invalidate();
    }
}
