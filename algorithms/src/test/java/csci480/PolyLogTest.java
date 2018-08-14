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

}
