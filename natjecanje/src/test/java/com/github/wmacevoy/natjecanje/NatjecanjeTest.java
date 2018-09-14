/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.natjecanje;

import java.io.FileInputStream;
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
public class NatjecanjeTest {
    
    public NatjecanjeTest() {
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
     * Test of main method, of class Natjecanje.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Natjecanje.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class Natjecanje.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Natjecanje instance = new Natjecanje();
        instance.read();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduce1 method, of class Natjecanje.
     */
    @Test
    public void testReduce1() {
        System.out.println("reduce1");
        Natjecanje instance = new Natjecanje();
        instance.reduce1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduce2 method, of class Natjecanje.
     */
    @Test
    public void testReduce2() {
        System.out.println("reduce2");
        Natjecanje instance = new Natjecanje();
        instance.reduce2();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minimize method, of class Natjecanje.
     */
    @Test
    public void testMinimize() {
        System.out.println("minimize");
        int i = 0;
        int j = 0;
        Natjecanje instance = new Natjecanje();
        int expResult = 0;
        int result = instance.minimize(i, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ok method, of class Natjecanje.
     */
    @Test
    public void testOk() {
        System.out.println("ok");
        int i = 0;
        Natjecanje instance = new Natjecanje();
        boolean expResult = false;
        boolean result = instance.ok(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of solve method, of class Natjecanje.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        Natjecanje instance = new Natjecanje();
        instance.solve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Natjecanje.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Natjecanje instance = new Natjecanje();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test public void testSolve1() {
        Natjecanje instance = new Natjecanje();
        instance.teams = 5;
        instance.haveBoat = new boolean[]{true,false,true,false,true};
        instance.haveReserve = new boolean[]{true,false,true,false,true};
        instance.solve();
        assertEquals(instance.missed,0);
        
    }
    
    @Test public void testSolve2() {
        Natjecanje instance = new Natjecanje();
        instance.teams = 5;
        instance.haveBoat = new boolean[]{true,false,true,false,true};
        instance.haveReserve = new boolean[]{false,false,true,false,false};
        instance.solve();
        assertEquals(instance.missed,1);
        
    }

    @Test public void testExample2() throws Exception {
        System.setIn(new FileInputStream("natjecanje.in.2"));
        Natjecanje.main(null);        
    }

}
