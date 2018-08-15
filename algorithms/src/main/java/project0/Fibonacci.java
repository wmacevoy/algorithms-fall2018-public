/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project0;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author wmacevoy
 */
public class Fibonacci {

    int ops;

    public int recursive(int k) {
        ops += 2;
        if (k <= 1) {
            ops += 1;
            return k;
        }
        ops += 5;
        return recursive(k - 1) + recursive(k - 2);
    }

    public int iterative(int k) {
        ops += 2;
        if (k <= 1) {
            ops += 1;
            return k;
        }

        ops += 2;
        int fkm2 = 0;
        int fkm1 = 1;
        ops += k * 2 + 2;
        for (int i = 1; i < k; ++i) {
            ops += 4;
            int fk = fkm1 + fkm2;
            fkm2 = fkm1;
            fkm1 = fk;
        }
        ops += 1;
        return fkm1;
    }

    static double PHI = (1 + Math.sqrt(5)) / 2;
    static double PSI = (1 - Math.sqrt(5)) / 2;

    public int direct(int k) {
        ops += 2;
        if (k <= 1) {
            ops += 1;
            return k;
        }
        ops += 4;
        return (int) Math.round((Math.pow(PHI, k) - Math.pow(PSI, k)) / (PHI - PSI));
    }

    public String[] headers = new String[]{"k", "fk-recursive", "ops-recursive", "fk-iterative", "iterative", "fk-direct", "direct"};
    public int[] data = new int[headers.length];

    public void row(int k) {
        int col = 0;
        data[col++] = k;
        ops = 0;
        data[col++] = recursive(k);
        data[col++] = ops;
        ops = 0;
        data[col++] = iterative(k);
        data[col++] = ops;
        ops = 0;
        data[col++] = direct(k);
        data[col++] = ops;
    }

    void csv(int n, PrintStream out) {
        for (int i = 0; i < headers.length; ++i) {
            if (i > 0) {
                out.print(",");
            }
            out.print(headers[i]);
        }
        out.println();
        for (int k = 0; k < n; ++k) {
            row(k);
            for (int i = 0; i < headers.length; ++i) {
                if (i > 0) {
                    out.print(",");
                }
                out.print(data[i]);
            }
            out.println();
        }
        out.close();
    }

    public static void main(String[] args) throws Exception {
        int n = args.length < 1 ? 15 : Integer.parseInt(args[0]);
        Fibonacci fibonacci = new Fibonacci();
        try (PrintStream out = new PrintStream(new FileOutputStream("fibonacci.csv"))) {
            fibonacci.csv(n, out);
        }
    }
}
