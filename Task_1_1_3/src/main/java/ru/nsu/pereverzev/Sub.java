package ru.nsu.pereverzev;

import java.io.IOException;

/**
 * expression, that subtract two other expressions.
 */
public class Sub extends Expression {
    Expression exA;
    Expression exB;

    Sub(Expression a, Expression b) {
        exA = a;
        exB = b;
    }

    @Override
    public double eval(String varsAsgn) throws Exception {
        return exA.eval(varsAsgn) - exB.eval(varsAsgn);
    }

    @Override
    public void print() throws IOException {
        Output.write("(");
        exA.print();
        Output.write("-");
        exB.print();
        Output.write(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Sub(exA.derivative(var), exB.derivative(var));
    }
}
