package com.example.jinny.expensesnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinny.expensesnote.R;
import com.example.jinny.expensesnote.utils.Utils;
import com.example.jinny.expensesnote.model.ExpenseModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity";

    private Spinner spnUser;
    private ExpenseModel expenseModel;
    private EditText edtNote;
    private EditText edtAmount;
    private TextView tvTime;
    private Button btnAdd;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setupUI();
    }

    private void setupUI() {
        expenseModel = new ExpenseModel();
        spnUser = findViewById(R.id.spn_user);
        final ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.user_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUser.setAdapter(arrayAdapter);
        spnUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                expenseModel.setUser(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        time = Calendar.getInstance().getTimeInMillis();

        edtNote = findViewById(R.id.edt_note);
        edtAmount = findViewById(R.id.edt_amount);
        tvTime = findViewById(R.id.tv_time);
        tvTime.setText(Utils.getTimeFromLong(time));
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        btnAdd = findViewById(R.id.bt_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkEmptyData()) {
                    add();
                } else {
                    Toast.makeText(AddActivity.this, "Please fill all field of data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showTimePicker() {
        SwitchDateTimeDialogFragment dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance("Pick Time", "Ok", "Cancel");
        dateTimeDialogFragment.startAtCalendarView();
        dateTimeDialogFragment.set24HoursMode(true);
        dateTimeDialogFragment.setMinimumDateTime(new GregorianCalendar(0, Calendar.JANUARY, 1).getTime());
        dateTimeDialogFragment.setMaximumDateTime(new GregorianCalendar(9999, Calendar.DECEMBER, 31).getTime());
        Calendar calendar = Calendar.getInstance();
        dateTimeDialogFragment.setDefaultDateTime(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH
        ), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)).getTime());

        //set date format
        try {
            dateTimeDialogFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("dd MMMM", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            Log.e(TAG, e.getMessage());
        }

        // Set listener
        dateTimeDialogFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                long t = date.getTime();
                time = t;
                tvTime.setText(Utils.getTimeFromLong(t));
//                Log.d(TAG, "onPositiveButtonClick: " + Utils.getTimeFromLong(t));
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Date is get on negative button click
            }
        });

        // Show
        dateTimeDialogFragment.show(getSupportFragmentManager(), "dialog_time");
    }

    private boolean checkEmptyData() {
        if (Utils.isEmpty(edtNote.getText().toString())) return true;
        if (Utils.isEmpty(edtAmount.getText().toString())) return true;
        return false;
    }

    private void add() {
        expenseModel.setAmount(Long.parseLong(edtAmount.getText().toString()));
        expenseModel.setNote(edtNote.getText().toString());
        expenseModel.setTime(time);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data");
        expenseModel.setId(MainActivity.id+1);
        databaseReference.child(Integer.toString(expenseModel.getId())).setValue(expenseModel);
        this.finish();
    }
}
