/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import csci480.PRNG;
import csci480.PolyLog;
import csci480.Stats;

/**
 *
 * @author wmacevoy
 */
public class QSWork {

    PRNG rng = new PRNG(1);

    int rndwork(int n) {
        if (n <= 1) {
            return 0;
        }
        int k = rng.next(0, n - 1);
        return n - 1 + work((k - 1) - (0) + 1) + work((n - 1) - (k + 1) + 1);
    }
    

    int work(int n) {
        if (n <= 1) {
            return 0;
        }
       int k = n/4;
        return n - 1 + work((k - 1) - (0) + 1) + work((n - 1) - (k + 1) + 1);
    }

    int avgWork(int n, double relErr) {
        Stats stats = new Stats();
        for (;;) {
            int required = (int) stats.requiredSamples(relErr);
            if (stats.samples >= required) {
                break;
            }
            while (stats.samples < required) {
                int w = rndwork(n);
                stats.sample(w);
            }
        }
        return (int) Math.round(stats.mean());
    }

    void powerEst() {
        PolyLog polyLog = new PolyLog();
        double relErr = 0.001;
        System.out.println("n,w,c,p,q");
        for (int k = 10; k < 28; ++k) {
            int n = 1 << k;
            //int w = avgWork(n,relErr);
            int w = rndwork(n);
            polyLog.sample(n, w);
            System.out.print(n + "," + w);
            System.out.print("," + polyLog.constant());
            System.out.print("," + polyLog.power());
            System.out.println("," + polyLog.logPower());
        }
    }

    public static void main(String[] args) throws Exception {
        QSWork qs = new QSWork();
        qs.powerEst();
    }

}
