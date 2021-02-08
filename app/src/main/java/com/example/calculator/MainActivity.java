package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CalculatorModel calculator;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] numbers = new int[]{
                R.id.zero_btn,
                R.id.one_btn,
                R.id.two_btn,
                R.id.three_btn,
                R.id.four_btn,
                R.id.five_btn,
                R.id.six_btn,
                R.id.seven_btn,
                R.id.eight_btn,
                R.id.nine_btn,
                R.id.dot_btn
        };
        int[] action = new int[]{
                R.id.multiply_btn,
                R.id.divide_btn,
                R.id.plus_btn,
                R.id.minus_btn,
                R.id.equals_btn
        };
        textView = findViewById(R.id.screen);
        calculator = new CalculatorModel();
        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumPressed(view.getId());
                textView.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onActionPressed(view.getId());
                textView.setText(calculator.getText());
            }
        };

        for (int number : numbers) {
            findViewById(number).setOnClickListener(numberButtonClickListener);
        }

        for (int value : action) {
            findViewById(value).setOnClickListener(actionButtonOnclickListener);
        }

        findViewById(R.id.clear_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.clear();
                textView.setText(calculator.getText());
            }
        });
        findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.delete();
                textView.setText(calculator.getText());
            }
        });
    }
}