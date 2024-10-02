package ru.nsu.pereverzev;

public class Mul extends Expression {
    Expression exA;
    Expression exB;

    Mul(Expression a, Expression b) {
        exA = a;
        exB = b;
    }

    @Override
    public int eval(String varsAsgn) {
        return exA.eval(varsAsgn) * exB.eval(varsAsgn);
    }

    @Override
    public void print() {
        System.out.print("(");
        exA.print();
        System.out.print("*");
        exB.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(exA.derivative(var), exB), new Mul(exA, exB.derivative(var)));
    }
}
