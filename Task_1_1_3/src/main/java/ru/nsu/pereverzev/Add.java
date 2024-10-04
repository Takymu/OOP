package ru.nsu.pereverzev;

public class Add extends Expression {
    Expression exA;
    Expression exB;

    Add(Expression a, Expression b) {
        exA = a;
        exB = b;
    }

    @Override
    public double eval(String varsAsgn) {
        return exA.eval(varsAsgn) + exB.eval(varsAsgn);
    }

    @Override
    public void print() {
        System.out.print("(");
        exA.print();
        System.out.print("+");
        exB.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Add(exA.derivative(var), exB.derivative(var));
    }
}
