#pragma once

namespace csci480 {

class PolyLog {
 private: int state;
 private: double n[4],fn[4],x[4],y[4],p[4],q[4],c[4];
 public: PolyLog();
 public: void reset();
 public: void sample(double n, double fn);
 public: bool valid() const;
 public: double power() const;
 public: double logPower() const;
 public: double constant() const;
 public: static void ok();
};

}
