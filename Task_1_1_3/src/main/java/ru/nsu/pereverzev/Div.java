package ru.nsu.pereverzev;

public class Div extends Expression {
    Expression exA;
    Expression exB;

    Div(Expression a, Expression b) {
        exA = a;
        exB = b;
    }

    @Override
    public double eval(String varsAsgn) {
        return exA.eval(varsAsgn) / exB.eval(varsAsgn);
    }

    @Override
    public void print() {
        System.out.print("(");
        exA.print();
        System.out.print("/");
        exB.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Div(new Add(new Mul(exA.derivative(var), exB), new Mul(exA, exB.derivative(var))), new Mul(exB, exB));
    }
}
