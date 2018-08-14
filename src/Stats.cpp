#include <iostream>
#include <math.h>
#include <assert.h>
#include "Stats.h"
#include "PRNG.h"

namespace csci480 {
  Stats::Stats() { reset(); }
  void Stats::reset() { samples = 0; sum=0; sum2=0; min = NAN; max = NAN; }

  void Stats::sample(double value) {
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

  double Stats::mean() const {
    return sum/samples;
  }

  double Stats::stddev() const {
    return sqrt((sum2-pow(sum,2)/samples)/(samples-1.0));
  }

  void Stats::ok() {
    Stats s;
    PRNG prng(1);
    int n = 10000;

    for (int i=0; i<n; ++i) {
      double x=prng.next(0,1000000)/1000000.0;
      s.sample(x);
    }

    assert(fabs(s.mean()-0.5)<1/sqrt(n));
    assert(fabs(s.stddev()-sqrt(1/12.0))<1/sqrt(n));    
  }
}

