package ru.nsu.pereverzev;

public abstract class Expression {
    public abstract int eval(String varsAsgn);
    public abstract void print();
    public abstract Expression derivative(String var);
}
