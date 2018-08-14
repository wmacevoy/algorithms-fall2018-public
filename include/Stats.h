#pragma once

namespace csci480 {

class Stats {
 public: Stats();
 public: void reset();
 public: int samples;
 public: double sum;
 public: double sum2;
 public: double min;
 public: double max;
 public: double mean() const;
 public: double stddev() const;
 public: void sample(double value);
 public: static void ok();
};

}
