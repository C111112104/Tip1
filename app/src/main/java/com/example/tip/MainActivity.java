package com.example.tip;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText amount_editText;
    SeekBar custom_seekBar;
    TextView custom;
    TextView tip15_textView;
    TextView total15_textView;
    TextView tipCustom_textView;
    TextView totalCustom_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount_editText = (EditText)findViewById(R.id.amount_editText);
        custom_seekBar = (SeekBar)findViewById(R.id.custom_seekBar);
        custom = (TextView)findViewById(R.id.custom);
        tip15_textView = (TextView)findViewById(R.id.tip15_textView);
        total15_textView = (TextView)findViewById(R.id.total15_textView);
        tipCustom_textView = (TextView)findViewById(R.id.tipCustom_textView);
        totalCustom_textView = (TextView)findViewById(R.id.totalCustom_textView);

        OnSeekBarChangeHandler seekBarHandler = new OnSeekBarChangeHandler();
        custom_seekBar.setOnSeekBarChangeListener(seekBarHandler);

        TextWatcherHandler editTextHandler = new TextWatcherHandler();
        amount_editText.addTextChangedListener(editTextHandler);
    }

    public class OnSeekBarChangeHandler implements SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
            try {
                custom.setText(String.format("%d", progress) + "%");
                double tipCustom = Double.parseDouble(amount_editText.getText().toString()) * custom_seekBar.getProgress() / 100.0;
                double totalCustom = Double.parseDouble(amount_editText.getText().toString()) * (1.0 + custom_seekBar.getProgress() / 100.0);
                tipCustom_textView.setText(String.format("%.2f", tipCustom));
                totalCustom_textView.setText(String.format("%.2f", totalCustom));
            }catch(NumberFormatException e){
                custom.setText(String.format("%d", progress) + "%");
            }
        }// End of onProgressChanged

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }
    public class TextWatcherHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, 	int count,int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            double tip15 = Double.parseDouble(amount_editText.getText().toString())*0.15;
            double total15 = Double.parseDouble(amount_editText.getText().toString())*1.15;
            tip15_textView.setText(String.format("%.2f" , tip15));
            total15_textView.setText(String.format("%.2f" , total15));
            double tipCustom = Double.parseDouble(amount_editText.getText().toString())*custom_seekBar.getProgress()/100;
            double totalCustom = Double.parseDouble(amount_editText.getText().toString())*(1.0+custom_seekBar.getProgress()/100.0);
            tipCustom_textView.setText(String.format("%.2f" , tipCustom));
            totalCustom_textView.setText(String.format("%.2f" , totalCustom));


        }// End of onTextChanged

        public void afterTextChanged(Editable s) {}
    }


}