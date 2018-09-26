/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almostperfect;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author wmacevoy
 */
public class Primes {
    static double[] errorBounds(int upperBound) {
        double maxError = 0;
        double minError = 0;
        BigInteger product = BigInteger.valueOf(1);
        int factors = 0;
        for (BigInteger prime = BigInteger.valueOf(2); product.longValue() <= upperBound;  prime = prime.nextProbablePrime()) {
            product = product.multiply(prime);
            factors += 1;
            double x = product.doubleValue();
            double estimate = Math.log(x)/Math.log(Math.log(x));
            if (estimate < factors) minError = Math.max(minError,factors-estimate);
            if (estimate > factors) maxError = Math.max(maxError,estimate-factors);
        }
        return new double[] { minError, maxError };
    }

    public static void main(String[] args) {
        System.out.println("Bounds: " + Arrays.toString(errorBounds(1_000_000_000)));
        ArrayList<BigInteger> primes = new ArrayList<BigInteger>();
        for (BigInteger bi = BigInteger.valueOf(2); Math.pow(bi.longValue(), 2) <= 1_000_000_000L; bi = bi.nextProbablePrime()) {
            primes.add(bi);
        }
        System.out.println("public static int[] primes = new int[] {");
        for (int i = 0; i < primes.size(); ++i) {
            System.out.print(primes.get(i));
            if (i != primes.size() - 1) {
                System.out.print(",");
            } else {
                System.out.println("}");

            }
            if (i % 10 == 0) {
                System.out.println("");
            }
        }

    }
}
