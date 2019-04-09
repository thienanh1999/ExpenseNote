package com.example.jinny.expensesnote.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jinny.expensesnote.R;
import com.example.jinny.expensesnote.utils.Utils;
import com.example.jinny.expensesnote.model.ExpenseModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpAdapterVH> {
    private static final String TAG = "ExpenseAdapter";

    private List<ExpenseModel> expenseModels;
    private Context context;
    private LayoutInflater layoutInflater;

    public ExpenseAdapter(List<ExpenseModel> expenseModels, Context context) {
        this.expenseModels = expenseModels;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ExpAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.expense_item, parent, false);
        return new ExpAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpAdapterVH holder, int position) {
        holder.setData(expenseModels.get(position));
    }

    @Override
    public int getItemCount() {
        return expenseModels.size();
    }

    public static class ExpAdapterVH extends RecyclerView.ViewHolder {
        private ImageView ivAva;
        private TextView tvNote;
        private TextView tvAmount;
        private TextView tvTime;

        public ExpAdapterVH(@NonNull View itemView) {
            super(itemView);
            ivAva = itemView.findViewById(R.id.iv_ava);
            tvNote = itemView.findViewById(R.id.tv_note);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvTime = itemView.findViewById(R.id.tv_time);
        }

        public void setData(ExpenseModel expenseModel) {
            if (expenseModel.getUser() == 0) {
                ivAva.setImageResource(R.drawable.ic_woman);
            } else {
                ivAva.setImageResource(R.drawable.ic_male);
            }

            tvNote.setText(expenseModel.getNote());
            tvAmount.setText(Long.toString(expenseModel.getAmount()) + " VND");
            tvTime.setText(Utils.getTimeFromLong(expenseModel.getTime()));

            Log.d(TAG, "setData: " + expenseModel.getNote());
        }
    }

}
