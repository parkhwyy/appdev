package edu.gsu.csc4360.hpark44.mvccalculator.interfaces;

public interface CInterface {

    public void pushOperand(String operand);

    public double pushOperator(String operator);

    public void reset();

}
