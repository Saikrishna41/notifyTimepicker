package com.tp.notificationexample;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.tp.notificationexample.databinding.ActivityWassupBinding;
import com.tp.notificationexample.ui.TimePickerFragment;

import static com.tp.notificationexample.utils.GlobalsKt.LOG_TAG;

public class wassupe extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private ActivityWassupBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWassupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(LOG_TAG, "oncreate called");

        Button btn = binding.tv;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        TextView textView = binding.timeText;

        textView.setText("Hour" + hourOfDay + "Minute" + minute);
    }
}
