/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci480;

import static java.lang.Math.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wmacevoy
 */
public class PolyLogTest {

    public PolyLogTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    static double f(double n) {
        return 3 * pow(n, 2.5) * pow(log(n), 0.5) + 4 * pow(n, 1.5) + 18;
    }

    /**
     * Test of reset method, of class PolyLog.
     */
    @Test
    public void testFit() {
        PolyLog fit = new PolyLog();

        for (int k = 2; k <= 6; ++k) {
            double n = pow(10.0, k);
            fit.sample(n, f(n));
        }

        assert (abs(fit.constant() - 3.0) / 3.0 < 1e-2);
        assert (abs(fit.power() - 2.5) / 2.5 < 1e-2);
        assert (abs(fit.logPower() - 0.5) / 0.5 < 1e-2);

    }

    @Test
    public void testValue1() {
        double c = 1.5, p = 2.4, q = 3.2, x = 4.1, eps = 1e-9;
        double y = c * pow(x, p) * pow(log(x), q);
        double result = PolyLog.value(c, p, q, x);
        assertEquals(y, result, eps);
    }

    @Test
    public void testValue2() {
        double c = 1.5, p = 0, q = 3.2, x = 4.1, eps = 1e-9;
        double y = c * pow(x, p) * pow(log(x), q);
        double result = PolyLog.value(c, p, q, x);
        assertEquals(y, result, eps);
    }

    @Test
    public void testValue3() {
        double c = 1.5, p = 2.4, q = 0, x = 4.1, eps = 1e-9;
        double y = c * pow(x, p) * pow(log(x), q);
        double result = PolyLog.value(c, p, q, x);
        assertEquals(y, result, eps);
    }

    @Test
    public void testInverse1() {
        double c = 1.5, p = 2.4, q = 3.2, x = 4.1, eps = 1e-9;
        double y = c * pow(x, p) * pow(log(x), q);
        double result = PolyLog.inverse(c, p, q, y);
        assertEquals(x, result, eps);
    }
    
    @Test
    public void testInverse2() {
        double c = 1.5, p = 0, q = 3.2, x = 4.1, eps = 1e-9;
        double y = c * pow(x, p) * pow(log(x), q);
        double result = PolyLog.inverse(c, p, q, y);
        assertEquals(x, result, eps);
    }

    @Test
    public void testInverse3() {
        double c = 1.5, p = 2.4, q = 0, x = 4.1, eps = 1e-9;
        double y = c * pow(x, p) * pow(log(x), q);
        double result = PolyLog.inverse(c, p, q, y);
        assertEquals(x, result, eps);
    }
    
    @Test
    public void testInverse4() {
        double c = 1, p = 0.5, q = -2.0, y = 1, eps = 1e-9;
        double x = PolyLog.inverse(c, p, q, y);
        double y1 = PolyLog.value(c, p, q, x);
        assertEquals(y, y1, eps);
        
    }
    
    @Test
    public void testInverse5() {
        double c = 1, p = 0.5, q = -0.5, y = 1, eps = 1e-9;
        double x = PolyLog.inverse(c, p, q, y);
        double y1 = PolyLog.value(c, p, q, x);
        assertEquals(y, y1, eps);
        
    }

    @Test
    public void testInverses() {
        double eps = 1e-6;
        for (double c : new double[] { 1.0 } ) {
            for (double p : new double[] { 0.0, 0.5, 1.0, 1.5, 2.0 }) {
                for (double q : new double[] { -2.0, -1.5, -0.5, 0, 0.5, 1.5, 2.0}) {
                    for (double y : new double[] { 1e3,1e6,1e9,1e12 }) {
                        if (p + q < 0.4) continue;
                        if (PolyLog.value(c, p, q, E) > y) continue;
                        if (PolyLog.value(c, p, q, 1e40) < y) continue;
                        System.out.println("p=" + p + " q=" + q +" y=" + y);
                        double x = PolyLog.inverse(c, p, q, y);
                        double y1 = PolyLog.value(c, p, q, x);
                        System.out.println("p=" + p + " q=" + q +" x=" + x + " y=" + y);
                        assertEquals("p=" + p + " q=" + q +" x=" + x + " y=" + y,0,log(y1/y),eps);
                        
                    }
                    
                }
            }
        }
        
    }
    
}
