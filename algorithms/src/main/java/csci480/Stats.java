/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci480;

/**
 *
 * @author wmacevoy
 */
public class Stats {
    public int samples;
    public double sum,sum2,min,max;
    public Stats() { reset(); }
    public void reset() { samples = 0; sum=0; sum2=0; min = Double.NaN; max = Double.NaN; }

  public void sample(double value) {
    if (samples == 0) {
      samples = 1;
      sum = value;
      sum2 = value*value;
      min = value;
      max = value;
    } else {
      ++samples;
      sum += value;
      sum2 += value*value;
      if (value < min) min = value;
      if (value > max) max = value;
    }
  }

  public double mean() {
    return sum/samples;
  }

  public double stddev() {
    return Math.sqrt((sum2-Math.pow(sum,2)/samples)/(samples-1.0));
  }

}
