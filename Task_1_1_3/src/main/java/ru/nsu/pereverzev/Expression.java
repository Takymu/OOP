package ru.nsu.pereverzev;

import java.io.IOException;

public abstract class Expression {
    public abstract double eval(String varsAsgn) throws Exception;

    public abstract void print() throws IOException;

    public abstract Expression derivative(String var);
}
