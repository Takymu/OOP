package ru.nsu.pereverzev;

public class Number extends Expression {
    double value;

    Number(double val) {
        value = val;
    }

    @Override
    public double eval(String varsAsgn) {
        return value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

}
