package ru.nsu.pereverzev;

import java.io.IOException;

/**
 * expression, that emulates constant.
 */
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
    public void print() throws IOException {
        Output.write(Double.toString(value));
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

}
