package com.example.unitconversioncalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Conversion conv;
    private EditText editThatText;
    private TextView topLabel;
    private TextView bottomLabel;
    private ImageButton switcher;
    private boolean isDefault;
    private int switchSimulator;
    private TextView output;
    private DecimalFormat df;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conv = new Conversion();
        editThatText = (EditText) findViewById(R.id.input_ET);
        topLabel = (TextView) findViewById(R.id.inputLabel);
        bottomLabel = (TextView) findViewById(R.id.outputLabel);
        switcher = (ImageButton) findViewById(R.id.switchButton);
        isDefault = true;
        switchSimulator = 0;
        output = (TextView) findViewById(R.id.output_TV);
        df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);

        editThatText.addTextChangedListener(inputTextWatcher);
        switcher.setOnClickListener(switchListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_ft_m:
                conv.setConvNumber(0);
                topLabel.setText(Conversion.labelNames[0]);
                bottomLabel.setText(Conversion.labelNames[1]);
                switchSimulator = 0;
                output.setText(df.format(conv.calculate()));
                isDefault = true;
                break;
            case R.id.menu_item_in_cm:
                conv.setConvNumber(2);
                topLabel.setText(Conversion.labelNames[2]);
                bottomLabel.setText(Conversion.labelNames[3]);
                switchSimulator = 2;
                output.setText(df.format(conv.calculate()));
                isDefault = true;
                break;
            case R.id.menu_item_miles_km:
                conv.setConvNumber(4);
                topLabel.setText(Conversion.labelNames[4]);
                bottomLabel.setText(Conversion.labelNames[5]);
                switchSimulator = 4;
                output.setText(df.format(conv.calculate()));
                isDefault = true;
        }
        return true;
    }

    private TextWatcher inputTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                conv.setUnit1(Integer.parseInt(charSequence.toString()));
                output.setText(df.format(conv.calculate()));
            } catch (NumberFormatException e) {
                conv.setUnit1(0);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            output.setText(df.format(conv.calculate()));
        }
    };

    private final View.OnClickListener switchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isDefault)
            {
                topLabel.setText(Conversion.labelNames[1 + switchSimulator]);
                bottomLabel.setText(Conversion.labelNames[switchSimulator]);
                conv.setConvNumber(conv.getConvNumber() + 1);
            }
            else {
                topLabel.setText(Conversion.labelNames[switchSimulator]);
                bottomLabel.setText(Conversion.labelNames[1 + switchSimulator]);
                conv.setConvNumber(conv.getConvNumber() - 1);
            }
            output.setText(df.format(conv.calculate()));
            isDefault = !isDefault;
        }


    };
}