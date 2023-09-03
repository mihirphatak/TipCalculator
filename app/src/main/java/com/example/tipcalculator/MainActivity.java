// Assignment 1
// TipCalculator
// Group 3 - Mihir Phatak and Aniket Sunil Shendre

package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText billValue;

    RadioGroup tipOptions;

    SeekBar seekBar;

    TextView seekBarValue;

    Button resetButton;

    Button calculateButton;

    TextView tipValue;

    TextView totalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billValue = findViewById(R.id.et_price_input);
        tipOptions = findViewById(R.id.rg_tip);
        seekBar = findViewById(R.id.sb_seekBar);
        seekBarValue = findViewById(R.id.tv_progress);
        resetButton = findViewById(R.id.bt_reset);
        calculateButton = findViewById(R.id.bt_calculate);
        tipValue = findViewById(R.id.tv_tip_calc);
        totalValue = findViewById(R.id.tv_total_calc);
        tipOptions.check(R.id.rb_10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billValue.setText("");
                tipOptions.check(R.id.rb_10);
                seekBar.setProgress(0);
                seekBarValue.setText("0%");
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = tipOptions.getCheckedRadioButtonId();
                String value = billValue.getText().toString();
                double tip = 0.0;
                double bill = 0.0;
                try {
                    bill = Double.parseDouble(value);
                    if(checked == R.id.rb_5) {
                        tip = 0.05;
                    }
                    else if(checked == R.id.rb_10) {
                        tip = 0.1;
                    }
                    else if (checked == R.id.rb_15) {
                        tip = 0.15;
                    }
                    else if(checked == R.id.rb_custom) {
                        tip = Double.parseDouble(seekBarValue.getText().toString()) / 100;
                    }
                    double calculatedTip = (bill * tip);
                    tipValue.setText(String.format("%.2f",calculatedTip));
                    totalValue.setText(String.format("%.2f", bill + calculatedTip));
                }
                catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Enter correct input.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}