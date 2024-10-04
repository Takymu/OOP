package ru.nsu.pereverzev;

import java.io.IOException;

public class Div extends Expression {
    Expression exA;
    Expression exB;

    Div(Expression a, Expression b) {
        exA = a;
        exB = b;
    }

    @Override
    public double eval(String varsAsgn) throws Exception {
        return exA.eval(varsAsgn) / exB.eval(varsAsgn);
    }

    @Override
    public void print() throws IOException {
        Output.write("(");
        exA.print();
        Output.write("/");
        exB.print();
        Output.write(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Div(new Add(new Mul(exA.derivative(var), exB),
                new Mul(exA, exB.derivative(var))), new Mul(exB, exB));
    }
}
