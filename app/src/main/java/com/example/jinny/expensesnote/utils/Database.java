package com.example.jinny.expensesnote.utils;

import android.content.Context;

import com.example.jinny.expensesnote.model.ExpenseModel;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance = null;

    private Context context;
    private List<ExpenseModel> expenseModels = new ArrayList<>();

    private Database(Context context)
    {
        this.context = context;
    }

    public static Database getInstance(Context context) {
        if (instance == null)
        {
            instance = new Database(context);
        }
        return instance;
    }

    public void setData(List<ExpenseModel> expenseModels) {
        this.expenseModels = expenseModels;
    }

    public List<ExpenseModel> getData() {
        return expenseModels;
    }
}
