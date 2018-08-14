#pragma once

// https://en.wikipedia.org/wiki/Xorshift

#include <stdint.h>

namespace csci480 {

  class PRNG {
  public: PRNG(int64_t seed=1);
  private: uint32_t state[4];
  public: void init(int64_t seed);
  private:  uint32_t next();
  public: int32_t next(int32_t a, int32_t b);
  public: static void ok();
  };
  
}
