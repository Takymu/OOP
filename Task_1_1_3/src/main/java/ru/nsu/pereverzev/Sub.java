package ru.nsu.pereverzev;

public class Sub extends Expression {
    Expression exA;
    Expression exB;

    Sub(Expression a, Expression b) {
        exA = a;
        exB = b;
    }

    @Override
    public int eval(String varsAsgn) {
        return exA.eval(varsAsgn) - exB.eval(varsAsgn);
    }

    @Override
    public void print() {
        System.out.print("(");
        exA.print();
        System.out.print("-");
        exB.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Sub(exA.derivative(var), exB.derivative(var));
    }
}
