package com.example.jinny.expensesnote.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jinny.expensesnote.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragement extends Fragment {


    public ChartFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_fragement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Boolean sex = getArguments().getBoolean("sex");
        TextView tvSex = view.findViewById(R.id.tv_sex);
        if (sex) tvSex.setText("him");
        else tvSex.setText("her");
    }
}
