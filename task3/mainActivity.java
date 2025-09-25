package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] numberBtnIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            input += b.getText().toString();
            display.setText(input);
        };

        for (int id : numberBtnIds) {
            findViewById(id).setOnClickListener(listener);
        }

        int[] operatorBtnIds = {
                R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv,
                R.id.btnDot
        };

        for (int id : operatorBtnIds) {
            findViewById(id).setOnClickListener(v -> {
                Button b = (Button) v;
                input += b.getText().toString();
                display.setText(input);
            });
        }

        findViewById(R.id.btnAC).setOnClickListener(v -> {
            input = "";
            display.setText("0");
        });

        findViewById(R.id.btnC).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                display.setText(input.isEmpty() ? "0" : input);
            }
        });

        findViewById(R.id.btnEq).setOnClickListener(v -> {
            try {
                double result = evaluateExpression(input);
                display.setText(String.valueOf(result));
                input = String.valueOf(result);
            } catch (Exception e) {
                display.setText("Error");
                input = "";
            }
        });
    }
