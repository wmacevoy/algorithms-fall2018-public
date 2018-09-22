/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci480;

import static java.lang.Math.*;

/**
 *
 * @author wmacevoy
 */
public class PolyLog {

    public PolyLog() {
        reset();
    }

    private int state;
    private double[] n = new double[4];
    private double[] fn = new double[4];
    private double[] x = new double[4];
    private double[] y = new double[4];
    private double[] p = new double[4];
    private double[] q = new double[4];
    private double[] c = new double[4];

    public void reset() {
        state = 0;
    }

    public void sample(double n0, double fn0) {
        if (state > 0 && n0 <= n[0]) {
            state = 0;
        }
        if (fn0 <= 0 || n0 <= 0) {
            state = 0;
            return;
        }

        n[3] = n[2];
        n[2] = n[1];
        n[1] = n[0];
        n[0] = n0;

        fn[3] = fn[2];
        fn[2] = fn[1];
        fn[1] = fn[0];
        fn[0] = fn0;

        if (state < 4) {
            ++state;
        }

        x[3] = x[2];
        x[2] = x[1];
        x[1] = x[0];
        if (state >= 2) {
            x[0] = (log(log(n[0])) - log(log(n[1]))) / (log(n[0]) - log(n[1]));
        } else {
            x[0] = 0;
        }
        y[3] = y[2];
        y[2] = y[1];
        y[1] = y[0];
        if (state >= 2) {
            y[0] = (log(fn[0]) - log(fn[1])) / (log(n[0]) - log(n[1]));
        } else {
            y[0] = 0;
        }

        q[3] = q[2];
        q[2] = q[1];
        q[1] = q[0];
        if (state >= 3) {
            q[0] = (y[0] - y[1]) / (x[0] - x[1]);
        } else {
            q[0] = 0;
        }

        p[3] = p[2];
        p[2] = p[1];
        p[1] = p[0];
        if (state >= 3) {
            p[0] = y[0] - q[0] * x[0];
        } else {
            p[0] = 0;
        }

        c[3] = c[2];
        c[2] = c[1];
        c[1] = c[0];
        if (state >= 3) {
            c[0] = fn[0] / (pow(n[0], p[0]) * pow(log(n[0]), q[0]));
        } else {
            c[0] = 0;
        }
    }

    public void power(double value) {
        p[0] = value;
    }

    public void logPower(double value) {
        q[0] = value;
    }

    public void constant(double value) {
        c[0] = value;
    }

    public double power() {
        return p[0];
    }

    public double logPower() {
        return q[0];
    }

    public double constant() {
        return c[0];
    }

    public boolean valid() {
        return state >= 3;
    }

    public static double value(double c, double p, double q, double x) {
        return c * Math.pow(x, p) * Math.pow(Math.log(x), q);
    }

    public double value(double x) {
        return value(constant(), power(), logPower(), x);
    }

    public static double inverse(double c, double p, double q, double y) {
        if (q == 0) {
            return Math.pow(y / c, 1 / p);
        }
        if (p == 0) {
            return Math.exp(Math.pow(y / c, 1 / q));
        }
        if (p / q > 0) {
            double z = 1 / q * Math.log(y / c) + Math.log(p / q);
            double x = exp(Math.min(z, 0)) + Math.max(z, 0);
            double eps0 = Double.MAX_VALUE, eps1 = Double.MAX_VALUE;
            for (;;) {
                double eps = (x + Math.log(x)) - z;
                x = x - (eps) / (1 + 1 / x);
                eps0 = Math.abs(eps);
                if (eps0 < 1e-6) {
                    if (eps0 < eps1) {
                        eps1 = eps0;
                    } else {
                        break;
                    }
                }
            }
            return Math.exp((q / p) * x);
        } else {
            double z = -1 / q * Math.log(y / c) + Math.log(-q / p);
            if (z < 1) {
                return Double.NaN;
            }
            double x = Math.max(1 + Math.sqrt(2 * (z - 1)), z);
            double eps0 = Double.MAX_VALUE, eps1 = Double.MAX_VALUE;
            for (;;) {
                double eps = (x - Math.log(x)) - z;
                x = x - (eps) / (1 - 1 / x);
                eps0 = Math.abs(eps);
                if (eps0 < 1e-6) {
                    if (eps0 < eps1) {
                        eps1 = eps0;
                    } else {
                        break;
                    }
                }
            }
            return Math.exp(-(q / p) * x);
        }

    }

    public double inverse(double y) {
        return inverse(constant(), power(), logPower(), y);
    }

}
