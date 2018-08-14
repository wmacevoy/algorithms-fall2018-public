/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci480;

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
public class StatsTest {

    public StatsTest() {
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

    /**
     * Test of reset method, of class Stats.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Stats s = new Stats();
        s.sample(1);
        s.sample(2);
        s.reset();
        assertEquals(0, s.samples);
    }

    /**
     * Test of sample method, of class Stats.
     */
    @Test
    public void testSample() {
        System.out.println("sample");
        double value1 = 3.0;
        double value2 = 5.0;
        double n = 2;
        double sum = value1 + value2;
        double sum2 = value1 * value1 + value2 * value2;
        double mean = (value1 + value2) / n;
        double stddev = Math.sqrt((Math.pow(value1 - mean, 2) + Math.pow(value2 - mean, 2)) / (n - 1));
        double eps = 1e-9;
        double min = Math.min(value1, value2);
        double max = Math.max(value1, value2);
        Stats s = new Stats();
        s.sample(value1);
        s.sample(value2);
        assertEquals(2, s.samples);
        assertEquals(sum, s.sum, eps);
        assertEquals(sum2, s.sum2, eps);
        assertEquals(mean, s.mean(), eps);
        assertEquals(stddev, s.stddev(), eps);
        assertEquals(min, s.min, eps);
        assertEquals(max, s.max, eps);
    }

    @Test
    public void testDistribution() {
        Stats s = new Stats();
        PRNG prng = new PRNG(1);
        int n = 10000;

        for (int i = 0; i < n; ++i) {
            double x = prng.next(0, 1000000) / 1000000.0;
            s.sample(x);
        }

        assertEquals(0.5, s.mean(), 1 / Math.sqrt(n));
        assertEquals(Math.sqrt(1 / 12.0), s.stddev(), 1 / Math.sqrt(n));
    }

}
