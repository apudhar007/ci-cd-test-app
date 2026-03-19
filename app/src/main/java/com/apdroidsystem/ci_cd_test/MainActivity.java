package com.apdroidsystem.ci_cd_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

        int[] numericButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            currentInput += b.getText().toString();
            tvDisplay.setText(currentInput);
        };

        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.btnC).setOnClickListener(v -> {
            currentInput = "";
            firstValue = Double.NaN;
            operator = "";
            tvDisplay.setText("0");
        });

        View.OnClickListener opListener = v -> {
            Button b = (Button) v;
            if (!currentInput.isEmpty()) {
                firstValue = Double.parseDouble(currentInput);
                operator = b.getText().toString();
                currentInput = "";
            }
        };

        findViewById(R.id.btnAdd).setOnClickListener(opListener);
        findViewById(R.id.btnSub).setOnClickListener(opListener);
        findViewById(R.id.btnMul).setOnClickListener(opListener);
        findViewById(R.id.btnDiv).setOnClickListener(opListener);

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
}