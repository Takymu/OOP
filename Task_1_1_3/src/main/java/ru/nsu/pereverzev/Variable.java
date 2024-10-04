package ru.nsu.pereverzev;

public class Variable extends Expression {
    String varname;

    Variable(String name) {
        varname = name;
    }

    @Override
    public double eval(String varsAsgn) {
        int i = varsAsgn.indexOf(varname) + varname.length() + 3;
        double val = 0;
        int bound = varsAsgn.length();
        int wasdot = 0;
        int start = i;
        while (i < bound && ((varsAsgn.charAt(i) >= '0' && varsAsgn.charAt(i) <= '9') || (varsAsgn.charAt(i) == '.' && wasdot == 0))) {
            if (varsAsgn.charAt(i) == '.') wasdot = 1;
            i++;
        }
        return Double.parseDouble(varsAsgn.substring(start, i));
    }

    @Override
    public void print() {
        System.out.print(varname);
    }

    @Override
    public Expression derivative(String var) {
        if (var == varname) {
            return new Number(1);
        } else {
            return this;
        }
    }
}
