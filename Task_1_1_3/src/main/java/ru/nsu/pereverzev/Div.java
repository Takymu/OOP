package ru.nsu.pereverzev;

public class Div extends Expression {
    Expression ex_a;
    Expression ex_b;

    Div(Expression a, Expression b) {
        ex_a = a;
        ex_b = b;
    }

    @Override
    public int eval(String varsAsgn) {
        return ex_a.eval(varsAsgn) / ex_b.eval(varsAsgn);
    }

    @Override
    public void print() {
        System.out.print("(");
        ex_a.print();
        System.out.print("/");
        ex_b.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Div(new Add(new Mul(ex_a.derivative(var), ex_b), new Mul(ex_a, ex_b.derivative(var))), new Mul(ex_b, ex_b));
    }
}
