package ru.nsu.pereverzev;

import java.io.IOException;

public class Variable extends Expression {
    String varname;

    Variable(String name) {
        varname = name;
    }

    private int evalGuard(String varsAsgn) {
        if(!varsAsgn.contains(varname))
            return -1;
        int i = varsAsgn.indexOf(varname) + varname.length();
        int len = varsAsgn.length();
        while (i < len && varsAsgn.charAt(i) == ' ') {
            i++;
        }
        if (i==len || varsAsgn.charAt(i) != '=')
            return -1;
        i++;
        while (i < len && varsAsgn.charAt(i) == ' ') {
            i++;
        }
        if (i==len || varsAsgn.charAt(i) <= '0' || varsAsgn.charAt(i) >= '9') {
            return -1;
        }
        return i;
    }

    @Override
    public double eval(String varsAsgn) throws Exception {
        int i = evalGuard(varsAsgn);
        if(i == -1)
            throw new Exception("invalid eval argument, variable value not found");
        int bound = varsAsgn.length();
        int wasdot = 0;
        int start = i;
        while (i < bound && ((varsAsgn.charAt(i) >= '0' && varsAsgn.charAt(i) <= '9')
                || (varsAsgn.charAt(i) == '.' && wasdot == 0))) {
            if (varsAsgn.charAt(i) == '.') wasdot = 1;
            i++;
        }
        return Double.parseDouble(varsAsgn.substring(start, i));
    }

    @Override
    public void print() throws IOException {
        Output.write(varname);
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
