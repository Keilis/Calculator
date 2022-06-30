package com.example.calculator.model;

public class CalculatorRealis implements CalculatorPerform {

    @Override
    public double perform(double arg1, double arg2, Operator operator) {
        switch (operator) {

            case MIN:
                return arg1-arg2;
            case DIV:
                return arg1 / arg2;
            case SUM:
                return arg1 + arg2;
            case MULT:
                return arg1 * arg2;
        }
        return 0.0;
    }
}
