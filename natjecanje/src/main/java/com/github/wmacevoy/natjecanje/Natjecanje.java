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

    public static void main(String[] args) {
        Natjecanje app = new Natjecanje();
        app.run();
    }
    int teams;
    int damaged;
    int reserves;
    boolean[] haveBoat, haveReserve;

    int missed;

    void read() {
        Scanner in = new Scanner(System.in);
        teams = in.nextInt();
        damaged = in.nextInt();
        reserves = in.nextInt();
        haveBoat = new boolean[teams];
        haveReserve = new boolean[teams];
        for (int i = 0; i < teams; ++i) {
            haveBoat[i] = true;
        }
        for (int i = 0; i < damaged; ++i) {
            int team = in.nextInt();
            haveBoat[team - 1] = false;
        }
        for (int i = 0; i < reserves; ++i) {
            int team = in.nextInt();
            haveReserve[team - 1] = true;
        }
    }

    void reduce1() { // use spares where team needs thiers
        for (int i = 0; i < teams; ++i) {
            if (!haveBoat[i] && haveReserve[i]) {
                haveBoat[i] = true;
                haveReserve[i] = false;
            }
        }
    }

    void reduce2() { // eliminate spares that don't matter or must go 1 place
        for (int i = 0; i < teams; ++i) {
            if (haveReserve[i]) {
                int uses = 0;
                if (i - 1 >= 0 && !haveBoat[i - 1]) {
                    ++uses;
                }
                if (i + 1 < teams && !haveBoat[i + 1]) {
                    ++uses;
                }
                if (uses == 0) {
                    haveReserve[i] = false;
                    --reserves;
                } else if (uses == 1) {
                    if (i - 1 >= 0 && !haveBoat[i - 1]) {
                        haveBoat[i - 1] = true;
                        haveReserve[i] = false;
                        --reserves;
                        i = Math.min(-1, i - 3);
                    }
                    if (i + 1 < teams && !haveBoat[i + 1]) {
                        haveBoat[i + 1] = true;
                        haveReserve[i] = false;
                        --reserves;
                    }
                }
            }
        }
    }

    int minimize(int i, int j) {
        if (i == j) {
            return (haveBoat[i] || haveReserve[i]) ? 0 : 1;
        }

        if (haveBoat[i] && !haveReserve[i]
                || !haveBoat[i] && haveReserve[i]) {
            return minimize(i + 1, j);
        }

        if (haveReserve[i]) {
            if (!haveBoat[i + 1]) {
                haveBoat[i + 1] = true;
                int partial = minimize(i + 1, j);
                haveBoat[i + 1] = false;
                return partial;
            } else {
                return minimize(i + 1, j);
            }
        } else {
            if (haveReserve[i + 1]) {
                int min0 = 1 + minimize(i + 1, j);
                haveBoat[i] = true;
                haveReserve[i + 1] = false;
                int min1 = minimize(i + 1, j);
                haveBoat[i] = false;
                haveReserve[i + 1] = true;
                return Math.min(min0, min1);
            } else {
                return 1 + minimize(i + 1, j);
            }
        }

        // throw new IllegalStateException();
    }

    boolean ok(int i) {
        return i < 0 || i >= teams || (haveBoat[i] && !haveReserve[i]);
    }

    void reduce3() {
        missed = 0;
        int i = 0;
        while (i < teams) {
            if (ok(i)) {
                ++i;
                continue;
            }
            int j = i;
            while (j < teams && (!ok(j + 1) || !ok(j + 2))) {
                ++j;
            }
            missed = missed + minimize(i, j);
        }
    }

    void solve() {
        reduce1();
//        reduce2();
        missed = minimize(0, teams - 1);
    }

    void run() {
        read();
        solve();
        System.out.println(missed);
    }
}
