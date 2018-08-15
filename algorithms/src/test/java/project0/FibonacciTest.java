/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project0;

import java.io.PrintStream;
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
public class FibonacciTest {
    
    public FibonacciTest() {
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

    static int[] f = new int[] {0,1,1,2,3,5,8,13,21};
    /**
     * Test of recursive method, of class Fibonacci.
     */
    @Test
    public void testRecursive() {
        Fibonacci instance = new Fibonacci();
        for (int k=0; k<f.length; ++k) {
            assertEquals(f[k],instance.recursive(k));
        }
    }

    /**
     * Test of iterative method, of class Fibonacci.
     */
    @Test
    public void testIterative() {
        Fibonacci instance = new Fibonacci();
        for (int k=0; k<f.length; ++k) {
            assertEquals(f[k],instance.iterative(k));
        }
    }

    /**
     * Test of direct method, of class Fibonacci.
     */
    @Test
    public void testDirect() {
        Fibonacci instance = new Fibonacci();
        for (int k=0; k<f.length; ++k) {
            assertEquals(f[k],instance.direct(k));
        }
    }

}
