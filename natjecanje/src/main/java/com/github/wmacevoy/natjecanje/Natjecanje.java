/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.natjecanje;

// https://open.kattis.com/problems/natjecanje

import java.util.Scanner;


/**
 *
 * @author wmacevoy
 */
public class Natjecanje {
    
    public static void main(String [] args) {
        Natjecanje app = new Natjecanje();
        app.run();
    }
    int teams;
    int damaged;
    int reserves;
    boolean [] haveBoat,haveReserve;
    void read() {
        Scanner in = new Scanner(System.in);
        teams = in.nextInt();
        damaged = in.nextInt();
        reserves = in.nextInt();
        haveBoat = new boolean[teams];
        haveReserve = new boolean[teams];
        for (int i=0; i<teams; ++i) { haveBoat[i]=true; }
        for (int i=0; i<damaged; ++i) {
            int team = in.nextInt();
            haveBoat[team-1]=false;
        }
        for (int i=0; i<damaged; ++i) {
            int team = in.nextInt();
            haveReserve[team-1]=true;
        }
    }
    
    void run() {
        read();
        solve();
        write();
    }
}
