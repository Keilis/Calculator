package com.example.calculator.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;
import com.example.calculator.model.CalculatorRealis;
import com.example.calculator.model.Operator;
import com.example.calculator.presenter.CalculatorPresenter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalculatorView {

    private static final String saveKeyNumber = "saveKeyNumber";
    private TextView resultTxt;
    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTxt = findViewById(R.id.key_resulting_field);

        presenter = new CalculatorPresenter(this,new CalculatorRealis());
        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);


        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_div, Operator.DIV);
        operators.put(R.id.key_mult, Operator.MULT);
        operators.put(R.id.key_mines, Operator.MIN);
        operators.put(R.id.key_plus, Operator.SUM);
        operators.put(R.id.key_result, Operator.RESULT);

        View.OnClickListener operatorsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.key_div).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_mult).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_mines).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_plus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_result).setOnClickListener(operatorsClickListener);

        findViewById(R.id.key_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onResetPressed();
            }
        });

        findViewById(R.id.key_drop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });

        findViewById(R.id.key_plumin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPluMinPressed();
            }
        });
        findViewById(R.id.key_square).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSquarePressed();
            }
        });
        findViewById(R.id.key_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRootPressed();
            }
        });

        if (savedInstanceState == null) {
            presenter.setArg1(0);
        } else {
            presenter.setArg1(savedInstanceState.getDouble(saveKeyNumber));
            if (presenter.getArg1() != 0) {
                resultTxt.setText(String.valueOf(presenter.getArg1()));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(saveKeyNumber, presenter.getArg1());
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}