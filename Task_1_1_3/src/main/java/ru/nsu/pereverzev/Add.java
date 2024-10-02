package ru.nsu.pereverzev;

public class Add extends Expression {
    Expression ex_a;
    Expression ex_b;

    Add(Expression a, Expression b) {
        ex_a = a;
        ex_b = b;
    }

    @Override
    public int eval(String varsAsgn) {
        return ex_a.eval(varsAsgn) + ex_b.eval(varsAsgn);
    }

    @Override
    public void print() {
        System.out.print("(");
        ex_a.print();
        System.out.print("+");
        ex_b.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var) {
        return new Add(ex_a.derivative(var), ex_b.derivative(var));
    }
}
