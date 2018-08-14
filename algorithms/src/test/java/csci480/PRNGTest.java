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
public class PRNGTest {

    public PRNGTest() {
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
    
    public static void testBins(long seed, int numBins, int numSamples) {
        PRNG prng = new PRNG(seed);
        int[] bins = new int[numBins];
        for (int i = 0; i < numSamples; ++i) {
            ++bins[prng.next(0, numBins - 1)];
        }

        double p = ((double) 1) / ((double) numBins);
        double mean = ((double) numSamples) / ((double) numBins);
        double stdDev = Math.sqrt(numSamples * (p * (1 - p)));

        for (int i = 0; i < numBins; ++i) {
            double z = (bins[i] - mean) / stdDev;
            if (Math.abs(z) >= 6) {
//        std::cout << "seed = " << seed << std::endl;        
//        std::cout << "numBins = " << numBins << std::endl;
//        std::cout << "numSamples = " << numSamples << std::endl;
//        std::cout << "p = " << p << std::endl;
//        std::cout << "mean = " << mean << std::endl;
//        std::cout << "stdDev = " << stdDev << std::endl;
//        std::cout << "x = " << bins[i] << std::endl;                                
//        std::cout << "i = " << i << std::endl;
//        std::cout << "z = " << z << std::endl;
                assert (Math.abs(z) < 6);
            }
        }
    }


    /**
     * Test of init method, of class PRNG.
     */
    @Test
    public void testSequence() {
        System.out.println("sequence");
        long seed = 101L;
        PRNG prng = new PRNG(seed);

        assertEquals(-8, prng.next(-9, 7));
        assertEquals(-1716125422, prng.next(Integer.MIN_VALUE, Integer.MAX_VALUE));

        assertEquals(3, prng.next(-9, 7));
        assertEquals(96111003, prng.next(Integer.MIN_VALUE, Integer.MAX_VALUE));

        assertEquals(-4, prng.next(-9, 7));
        assertEquals(1736830095, prng.next(Integer.MIN_VALUE, Integer.MAX_VALUE));

        assertEquals(-4, prng.next(-9, 7));
        assertEquals(1692926503, prng.next(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    /**
     * Test of next method, of class PRNG.
     */
    @Test
    public void testBins() {
        System.out.println("bins");
                long[] seeds = new long[]{0, 1, -1, 5, -5};
        for (long seed : seeds) {
            for (int bins = 2; bins <= 32; ++bins) {
                testBins(seed, bins, 100000);
            }
        }
    }
}
