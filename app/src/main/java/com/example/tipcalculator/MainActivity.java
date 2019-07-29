package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edtAmount;
    private TextView txtPercent, txtTip, txtTotal, txtAmount;
    private SeekBar sebPercent;

    private double amount = 0.0;
    private double percent = 0.15;


    private static final NumberFormat numberCurrency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat numberPercent = NumberFormat.getPercentInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addControls();
        addEvents();
        
    }

    private void addEvents() {
        txtPercent.setText(numberPercent.format(percent));
        txtTip.setText(numberCurrency.format(0));
        txtTotal.setText(numberCurrency.format(0));


        edtAmount.addTextChangedListener(amountTextWatcher);
        sebPercent.setOnSeekBarChangeListener(percentSeekBarChange);

    }

    private void addControls() {
        edtAmount = findViewById(R.id.edtAmount);
        txtAmount = findViewById(R.id.txtAmount);
        txtTip = findViewById(R.id.txtTip);
        txtTotal = findViewById(R.id.txtTotal);
        txtPercent = findViewById(R.id.txtPercent);
        sebPercent = findViewById(R.id.seekBarPercent);
    }


    private void tipCal(){
        double tip = amount * percent ;
        double total = tip + amount;

        txtTip.setText(numberCurrency.format(tip));
        txtTotal.setText(numberCurrency.format(total));
    }

    private final TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{

                amount = Double.parseDouble(charSequence.toString()) /100;
                txtAmount.setText(numberCurrency.format(amount));

            }catch (Exception ex){
                txtAmount.setText(numberCurrency.format(0));
                amount = 0;
            }
            tipCal();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final SeekBar.OnSeekBarChangeListener percentSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            percent = (double) i / 100;
            txtPercent.setText(numberPercent.format(percent));
            tipCal();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
