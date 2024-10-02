package ru.nsu.pereverzev;

public class Number extends Expression {
    int value;

    Number(int val) {value = val;}

    @Override
    public int eval(String varsAsgn) {
        return value;
    }
    @Override
    public void print() {
        System.out.print(value);
    }
    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

}
