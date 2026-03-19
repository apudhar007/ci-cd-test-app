package com.apdroidsystem.ci_cd_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.apdroidsystem.ci_cd_test.R;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = Double.NaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Numeric buttons
        findViewById(R.id.btn0).setOnClickListener(numericListener);
        findViewById(R.id.btn1).setOnClickListener(numericListener);
        findViewById(R.id.btn2).setOnClickListener(numericListener);
        findViewById(R.id.btn3).setOnClickListener(numericListener);
        findViewById(R.id.btn4).setOnClickListener(numericListener);
        findViewById(R.id.btn5).setOnClickListener(numericListener);
        findViewById(R.id.btn6).setOnClickListener(numericListener);
        findViewById(R.id.btn7).setOnClickListener(numericListener);
        findViewById(R.id.btn8).setOnClickListener(numericListener);
        findViewById(R.id.btn9).setOnClickListener(numericListener);

        // Operation buttons
        findViewById(R.id.btnAdd).setOnClickListener(opListener);
        findViewById(R.id.btnSub).setOnClickListener(opListener);
        findViewById(R.id.btnMul).setOnClickListener(opListener);
        findViewById(R.id.btnDiv).setOnClickListener(opListener);

        // Action buttons
        findViewById(R.id.btnC).setOnClickListener(v -> {
            currentInput = "";
            firstValue = Double.NaN;
            operator = "";
            tvDisplay.setText("0");
        });

        findViewById(R.id.btnEq).setOnClickListener(v -> {
            if (!Double.isNaN(firstValue) && !currentInput.isEmpty()) {
                double secondValue = Double.parseDouble(currentInput);
                double result = 0;
                switch (operator) {
                    case "+": result = firstValue + secondValue; break;
                    case "-": result = firstValue - secondValue; break;
                    case "*": result = firstValue * secondValue; break;
                    case "/": result = firstValue / secondValue; break;
                }
                tvDisplay.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                firstValue = Double.NaN;
            }
        });
    }

    private final View.OnClickListener numericListener = v -> {
        Button b = (Button) v;
        currentInput += b.getText().toString();
        tvDisplay.setText(currentInput);
    };

    private final View.OnClickListener opListener = v -> {
        Button b = (Button) v;
        if (!currentInput.isEmpty()) {
            firstValue = Double.parseDouble(currentInput);
            operator = b.getText().toString();
            currentInput = "";
        }
    };
}