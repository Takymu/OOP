package ru.nsu.pereverzev;

import java.io.IOException;
import java.io.OutputStream;

/**
 * expression, that adds two other expressions.
 */
public class Add extends Expression {
    Expression exA;
    Expression exB;

    Add(Expression a, Expression b) {
        exA = a;
        exB = b;
    }

    @Override
    public double eval(String varsAsgn) throws Exception {
        return exA.eval(varsAsgn) + exB.eval(varsAsgn);
    }

    @Override
    public void print() throws IOException {
        Output.write("(");
        exA.print();
        Output.write("+");
        exB.print();
        Output.write(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Add(exA.derivative(var), exB.derivative(var));
    }
}
