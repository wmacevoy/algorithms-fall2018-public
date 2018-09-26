/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almostperfect;

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
public class AlmostPerfectTest {
    
    public AlmostPerfectTest() {
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
     * Test of main method, of class AlmostPerfect.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AlmostPerfect.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sumOfFactors method, of class AlmostPerfect.
     */
    @Test
    public void testSumOfFactorsBillion() {
        System.out.println("sumOfFactors");
        int n = 0;
        AlmostPerfect instance = new AlmostPerfect();
        int expResult = 1497558338;
        int result = instance.sumOfFactors(1_000_000_000);
        assertEquals(expResult, result);
    }

    /**
     * Test of message method, of class AlmostPerfect.
     */
    @Test
    public void testMessage() {
        System.out.println("message");
        int n = 0;
        AlmostPerfect instance = new AlmostPerfect();
        String expResult = "";
        String result = instance.message(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class AlmostPerfect.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        AlmostPerfect instance = new AlmostPerfect();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of solve method, of class AlmostPerfect.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        AlmostPerfect instance = new AlmostPerfect();
        instance.solve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intSqrt method, of class AlmostPerfect.
     */
    @Test
    public void testIntSqrt() {
        System.out.println("intSqrt");
        int n = 0;
        int expResult = 0;
        int result = AlmostPerfect.intSqrt(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intPow method, of class AlmostPerfect.
     */
    @Test
    public void testIntPow() {
        System.out.println("intPow");
        int a = 0;
        int b = 0;
        int expResult = 0;
        int result = AlmostPerfect.intPow(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of smallPrimeFactorization method, of class AlmostPerfect.
     */
    @Test
    public void testSmallPrimeFactorization() {
        System.out.println("smallPrimeFactorization");
        int n = 0;
        AlmostPerfect instance = new AlmostPerfect();
        int[][] expResult = null;
        int[][] result = instance.smallPrimeFactorization(n);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
