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
