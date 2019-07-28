package edu.gsu.csc4360.hpark44.mvccalculator.model;


import edu.gsu.csc4360.hpark44.mvccalculator.interfaces.CInterface;
import android.util.Log;
import java.util.Stack;

public class CalculatorModel implements CInterface {

    private static String LOG_TAG = "Model";

    private Stack<String> DataStack = new Stack<String>();
    private boolean isOperator = false;

    public static double calculate(Stack<String> stack) {
        double tmpResult = 0;
        double tmpOperand = Double.valueOf(stack.pop());
        if(stack.isEmpty()) {
            return tmpOperand;
        }

        String tmpOperator = stack.pop();
        if(tmpOperator.equals("+")) {
            tmpResult = calculate(stack) + tmpOperand;
        } else if(tmpOperator.equals("-")) {
            tmpResult = calculate(stack) - tmpOperand;
        } else if(tmpOperator.equals("x")) {
            tmpResult = calculate(stack) * tmpOperand;
        } else if(tmpOperator.equals("÷")) {
            tmpResult = calculate(stack) / tmpOperand;
        }
        return tmpResult;
    }

    @Override
    public void pushOperand(String operand) {
        DataStack.add(operand);
        isOperator = false;
    }

    @Override
    public double pushOperator(String operate) {
        double tmpResult = 0;
        if(isOperator) {
            DataStack.pop();
        }
        if(operate.equals("=")) {
            tmpResult = calculate(DataStack);
            DataStack.add(String.valueOf(tmpResult));
        } else {
            Stack<String> tmpStack = (Stack<String>) DataStack.clone();
            tmpResult = calculate(tmpStack);
            DataStack.add(operate);
            isOperator = true;
        }

        return tmpResult;
    }

    @Override
    public void reset() {
        DataStack.removeAllElements();
        isOperator = false;
    }
}
