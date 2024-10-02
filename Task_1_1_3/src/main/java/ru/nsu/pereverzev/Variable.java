package ru.nsu.pereverzev;

public class Variable extends Expression {
    String varname;

    Variable(String name) {
        varname = name;
    }

    @Override
    public int eval(String varsAsgn) {
        int i = varsAsgn.indexOf(varname) + varname.length() + 3;
        int val = 0;
        int bound = varsAsgn.length();
        while (i < bound && varsAsgn.charAt(i) >= '0' && varsAsgn.charAt(i) <= '9') {
            val *= 10;
            val += (varsAsgn.charAt(i) - '0');
            i++;
        }
        return val;
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
