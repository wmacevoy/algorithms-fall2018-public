#include <assert.h>
#include <iostream>
#include <vector>
#include <math.h>

#include "PRNG.h"

namespace csci480 {

  PRNG::PRNG(int64_t seed) {
    init(seed);
  }

  void PRNG::init(int64_t seed) {
    state[0]=seed;
    state[1]=seed >> 32;
    state[2]=~seed;
    state[3]=~seed >> 32;
    for (int i=0; i<8; ++i) next();
  }

  uint32_t PRNG::next() {
    uint32_t s, t = state[3];
    t ^= t << 11;
    t ^= t >> 8;
    state[3] = state[2];
    state[2] = state[1];
    state[1] = s = state[0];
    t ^= s;
    t ^= s >> 19;	
    state[0] = t;
    return t;
  }

  int32_t PRNG::next(int32_t a, int32_t b) {
    if (b <= a) return a;
    uint64_t n = ((int64_t) b) - ((int64_t) a) + 1L;
    uint64_t x = (((uint64_t) next()) << 32) | next();
    return int32_t(a + (x % n));
  }

  static void testBins(int64_t seed, int numBins, int numSamples) {
    PRNG prng(seed);
    std::vector<int> bins(numBins,0);
    for (int i=0; i<numSamples; ++i) {
      ++bins.at(prng.next(0,numBins-1));
    }
    
    double p = double(1)/double(numBins);
    double mean = double(numSamples)/double(numBins);    
    double stdDev = sqrt(numSamples*(p*(1-p)));
    
    for (int i=0; i<numBins; ++i) {
      double z=(bins[i]-mean)/stdDev;
      if (fabs(z) >= 6) {
        std::cout << "seed = " << seed << std::endl;        
        std::cout << "numBins = " << numBins << std::endl;
        std::cout << "numSamples = " << numSamples << std::endl;
        std::cout << "p = " << p << std::endl;
        std::cout << "mean = " << mean << std::endl;
        std::cout << "stdDev = " << stdDev << std::endl;
        std::cout << "x = " << bins[i] << std::endl;                                
        std::cout << "i = " << i << std::endl;
        std::cout << "z = " << z << std::endl;
        assert(fabs(z) < 6);
      }
    }
  }
  
  void PRNG::ok() {
    PRNG prng(101);

    assert(prng.next(1,4) == 1);
    assert(prng.next(INT32_MIN,INT32_MAX) == -1716125422);
    
    assert(prng.next(1,4) == 2);
    assert(prng.next(INT32_MIN,INT32_MAX) == 96111003);
    
    assert(prng.next(1,4) == 2);
    assert(prng.next(INT32_MIN,INT32_MAX) == 1736830095);
    
    assert(prng.next(1,4) == 2);
    assert(prng.next(INT32_MIN,INT32_MAX) == 1692926503);
    
    int64_t seeds[] = {0,1,-1,5,-5};
    for (auto seed : seeds) {
      for (int bins = 2; bins <= 32; ++bins) {
        testBins(seed,bins,100000);
      }
    }

    
  }

}
