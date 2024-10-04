package ru.nsu.pereverzev;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    public void mainTest() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldps = System.out;
        System.setOut(ps);

        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))
        e.print();
        System.out.flush();
        assertEquals("(3.0+(2.0*x))", baos.toString());
        baos.reset();

        Expression de = e.derivative("x");
        de.print();
        System.out.flush();
        assertEquals("(0.0+((0.0*x)+(2.0*1.0)))", baos.toString());
        baos.reset();

        Expression e1 = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))
        double result = e1.eval("x = 10; y = 13");
        assertEquals(23.0, result);

        Expression e2 = new Div(new Number(10), new Sub(new Variable("x"),
                new Variable("var1"))); // 10 / (x - var1)
        e2.print();
        System.out.flush();
        assertEquals("(10.0/(x-var1))", baos.toString());
        baos.reset();

        Expression e2d = e2.derivative("x");
        e2d.print();
        System.out.flush();
        assertEquals("(((0.0*(x-var1))+(10.0*(1.0-var1)))/((x-var1)*(x-var1)))", baos.toString());
        result = e2.eval("x = 1244; var1 = 1239");
        assertEquals(2.0, result);

    }

}