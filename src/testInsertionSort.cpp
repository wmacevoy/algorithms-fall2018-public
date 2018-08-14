#include <math.h>
#include "PRNG.h"
#include "PolyLog.h"
#include "Stats.h"
#include "InsertionSort.h"

void bestCaseInsertionSort() {
  csci480::PolyLog fit;
  std::cout << "bestCaseInsertionSort" << std::endl;
  std::cout << "n,ops,c,p,q" << std::endl;
  
  for (int k=8; k<=20; ++k) {
    size_t n = 1 << k; // 2^k
    std::vector < int > a(n,0);
    for (size_t i=0; i<n; ++i) {
      a[i]=i;
    }
    uint64_t ops = csci480::InsertionSort::work(a);

    fit.sample(n,ops);

    if (fit.valid()) {
      std::cout << n << "," << ops << "," << fit.constant() << "," << fit.power() << "," << fit.logPower() << std::endl;
    }
  }
}


void worstCaseInsertionSort() {
  csci480::PolyLog fit;
  std::cout << "worstCaseInsertionSort" << std::endl;
  std::cout << "n,ops,c,p,q" << std::endl;
  
  for (int k=8; k<=12; ++k) {
    size_t n = 1 << k; // 2^k
    std::vector < int > a(n,0);
    for (size_t i=0; i<n; ++i) {
      a[i]=n-i-1;
    }
    uint64_t ops = csci480::InsertionSort::work(a);

    fit.sample(n,ops);

    if (fit.valid()) {
      std::cout << n << "," << ops << "," << fit.constant() << "," << fit.power() << "," << fit.logPower() << std::endl;
    }
  }
}

void avgCaseInsertionSort() {
  csci480::PolyLog fit;
  csci480::PRNG prng;
  int df = 24;
  std::cout << "avgCaseInsertionSort" << std::endl;
  std::cout << "n,ops,c,p,q,df,logstddev" << std::endl;
  
  for (int k=8; k<=15; ++k) {
    size_t n = 1 << k; // 2^k
    std::vector < int > a(n,0);
    csci480::Stats opsStats;

    for (;;) {
      for (size_t i=0; i<n; ++i) {
        a[i]=prng.next(0,1000000);
      }
      uint64_t ops = csci480::InsertionSort::work(a);
      opsStats.sample(log(ops));

      if (opsStats.samples > 24 && opsStats.stddev() / sqrt(opsStats.samples) < 0.001) {
        fit.sample(n,exp(opsStats.mean()));
        break;
      }
    }

    if (fit.valid()) {
      std::cout << n << "," << exp(opsStats.mean()) << "," << fit.constant() << "," << fit.power() << "," << fit.logPower() << "," << opsStats.samples << "," << opsStats.stddev() << std::endl;
    }
  }
}

int main()
{
  csci480::InsertionSort::ok();
  
  bestCaseInsertionSort();
  worstCaseInsertionSort();
  avgCaseInsertionSort();  
  std::cout << "testInsertionSort ok" << std::endl;  
  return 0;
}

