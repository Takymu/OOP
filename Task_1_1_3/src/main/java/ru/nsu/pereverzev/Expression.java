package ru.nsu.pereverzev;

public abstract class Expression {
    public abstract double eval(String varsAsgn);

    public abstract void print();

    public abstract Expression derivative(String var);
}
