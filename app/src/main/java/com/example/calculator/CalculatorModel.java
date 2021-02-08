package com.example.calculator;

public class CalculatorModel {
    private double firstArg;
    private double secondArg;

    private StringBuilder inputStr = new StringBuilder();

    private int actionSelected;

    private State state;

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }

    public CalculatorModel() {
        state = State.firstArgInput;
    }

    public void onNumPressed(int buttonId) {

        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 9) {
            switch (buttonId) {
                case R.id.zero_btn:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.one_btn:
                    inputStr.append("1");
                    break;
                case R.id.two_btn:
                    inputStr.append("2");
                    break;
                case R.id.three_btn:
                    inputStr.append("3");
                    break;
                case R.id.four_btn:
                    inputStr.append("4");
                    break;
                case R.id.five_btn:
                    inputStr.append("5");
                    break;
                case R.id.six_btn:
                    inputStr.append("6");
                    break;
                case R.id.seven_btn:
                    inputStr.append("7");
                    break;
                case R.id.eight_btn:
                    inputStr.append("8");
                    break;
                case R.id.nine_btn:
                    inputStr.append("9");
                    break;
                case R.id.dot_btn:
                    inputStr.append(".");
                    break;
            }
        }

    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.equals_btn && state == State.secondArgInput && inputStr.length() > 0) {
            secondArg = Double.parseDouble(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.plus_btn:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.minus_btn:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.multiply_btn:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.divide_btn:
                    inputStr.append(firstArg / secondArg);
                    break;
            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput) {
            firstArg = Double.parseDouble(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return inputStr.toString();
            case operationSelected:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case secondArgInput:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr)
                        .toString();
            case resultShow:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(inputStr.toString())
                        .toString();
        }
    }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.plus_btn:
                return '+';
            case R.id.minus_btn:
                return '-';
            case R.id.multiply_btn:
                return '*';
            case R.id.divide_btn:
            default:
                return '/';

        }
    }

    public void clear() {
        state = State.firstArgInput;
        inputStr.setLength(0);
    }

    public void delete() {
        if (inputStr.length() > 0) {

            inputStr.setLength(inputStr.length() - 1);
            state = State.firstArgInput;
        }

    }
}

