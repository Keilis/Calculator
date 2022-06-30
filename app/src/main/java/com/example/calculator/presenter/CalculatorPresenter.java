package com.example.calculator.presenter;

import com.example.calculator.model.CalculatorPerform;
import com.example.calculator.model.Operator;
import com.example.calculator.model.Types;
import com.example.calculator.view.ui.CalculatorView;

import java.text.DecimalFormat;

public class CalculatorPresenter {
    private DecimalFormat formater = new DecimalFormat("#.##");
    private CalculatorView view;
    private CalculatorPerform calculator;

    private double arg1;
    private Double arg2;


    private Operator selectedOperator;
    private Operator lastOperator;
    private Types types = Types.INT;

    public CalculatorPresenter(CalculatorView view, CalculatorPerform calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(double digit) {
        if (selectedOperator == Operator.RESULT){
            reset();
        }
        if(types == Types.INT) {
            if (arg2 == null) {
                arg1 = arg1*10 + digit;
                showFormatted(arg1);
            } else {
                arg2 = arg2*10 + digit;
                showFormatted(arg2);
            }
        } else {
            if (arg2 == null) {
                arg1 = arg1 + digit/10;
                showFormatted(arg1);
            } else {
                arg2 = arg2 + digit/10;
                showFormatted(arg2);
            }
        }
    }

    public void onOperatorPressed(Operator operator) {
        if (operator == Operator.RESULT && arg2 == null) return;
        if (selectedOperator == Operator.RESULT && selectedOperator == operator) {
            selectedOperator = lastOperator;
            lastOperator = null;
        }
        if (selectedOperator != null && lastOperator == null) {
            arg1 = calculator.perform(arg1, arg2, (lastOperator !=null ? lastOperator : selectedOperator));
            lastOperator = operator == Operator.RESULT ? selectedOperator : null;
            showFormatted(arg1);
        }
        arg2 = operator == Operator.RESULT ? arg2 : 0.0;
        lastOperator = operator == Operator.RESULT ? selectedOperator : null;
        selectedOperator = operator;
        types = Types.INT;

    }
    private void reset(){
        arg1 = 0.0;
        arg2 = null;
        selectedOperator = null;
        types = Types.INT;
    }

    public void onResetPressed() {
        reset();
        showFormatted(arg1);
    }

    public void onDotPressed() {
        types = Types.DOUBLE;
    }

    public void onSquarePressed() {
        arg1 = arg1*arg1;
        showFormatted(arg1);
    }

    public void onRootPressed() {
        arg1 = Math.sqrt(arg1);
        showFormatted(arg1);
    }

    public void onPluMinPressed() {
        arg1 = arg1*(-1);
        showFormatted(arg1);
    }

    private void showFormatted(double value){
        view.showResult((formater.format(value)));
    }

    public double getArg1() {
        return arg1;
    }

    public void setArg1(double arg1) {
        this.arg1 = arg1;
    }
}
