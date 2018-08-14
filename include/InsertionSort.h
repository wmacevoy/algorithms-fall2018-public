#pragma once

#include <vector>
#include <functional>
#include <assert.h>
#include <iostream>
#include "PRNG.h"

namespace csci480 {

  class InsertionSort {
  public:
    template<typename T, typename Cmp = std::less<T>>
      static void sort(std::vector<T> &data) {
      Cmp cmp;
      size_t n = data.size();
      for (size_t i = 1; i<n; ++i) {
        size_t j = i;
        while (j > 0 && !cmp(data[j-1],data[i])) --j;
        if (j != i) {
          int k = i;
          T tmp = data[i];
          while (k > j) {
            data[k]=data[k-1];
            --k;
          }
          data[j]=tmp;
        }
      }
    }

    template<typename T, typename Cmp = std::less<T>>
      static uint64_t work(std::vector<T> &data) {
      uint64_t ops = 0;
      Cmp cmp;
      size_t n = data.size();
      for (size_t i = 1; i<n; ++i) {
        size_t j = i;
        while (j > 0 && (ops+=3,!cmp(data[j-1],data[i]))) --j;
        if (j != i) {
          int k = i;
          ops += 1;
          T tmp = data[i];
          while (k > j) {
            ops += 2;
            data[k]=data[k-1];
            --k;
          }
          ops += 1;
          data[j]=tmp;
        }
      }
      return ops;
    }
    

  public: static void ok() {
      PRNG prng(1);
      for (int n=0; n<100; ++n) {
        for (int t=0; t<10; ++t) {
          std::vector<int> a(n,0);
          for (int i=0; i<n; ++i) {
            a[i]=prng.next(-10,10);
          }
          std::vector<int> b=a;
          InsertionSort::sort(b);

          bool sorted = true;
          for (int i=1; i<n; ++i) {
            sorted = sorted && (b[i-1] <= b[i]);
          }

          if (!sorted) {
            for (int i=0; i<n; ++i) {
              std::cout << " a[" << i << "]="  << a[i];
            }
            std::cout << std::endl;
            for (int i=0; i<n; ++i) {
              std::cout << " b[" << i << "]="  << b[i];
            }
            std::cout << std::endl;
          }
          assert(sorted);
        }
      }
    }
  };
}
