#include <iostream>
#include <assert.h>
#include <math.h>
#include "PolyLog.h"

namespace csci480 {

  PolyLog::PolyLog() { reset(); }

  void PolyLog::reset() { state = 0; }

  void PolyLog::sample(double n0, double fn0) {
    if (state > 0 && n0 <= n[0]) { state = 0; }
    if (fn0 <= 0 || n0 <= 0) {
      state = 0;
      return;
    }

    n[3]=n[2];
    n[2]=n[1];
    n[1]=n[0];
    n[0]=n0;

    fn[3]=fn[2];
    fn[2]=fn[1];
    fn[1]=fn[0];
    fn[0]=fn0;

    if (state < 4) ++state;

    x[3]=x[2];
    x[2]=x[1];
    x[1]=x[0];
    if (state >= 2) {
      x[0]=(log(log(n[0]))-log(log(n[1])))/(log(n[0])-log(n[1]));
    } else {
      x[0]=0;
    }
    y[3]=y[2];
    y[2]=y[1];
    y[1]=y[0];
    if (state >= 2) {
      y[0]=(log(fn[0])-log(fn[1]))/(log(n[0])-log(n[1]));
    } else {
      y[0]=0;
    }

    q[3]=q[2];
    q[2]=q[1];
    q[1]=q[0];
    if (state >= 3) {
      q[0]=(y[0]-y[1])/(x[0]-x[1]);
    } else {
      q[0]=0;
    }

    p[3]=p[2];
    p[2]=p[1];
    p[1]=p[0];
    if (state >= 3) {
      p[0]=y[0]-q[0]*x[0];
    } else {
      p[0]=0;
    }

    c[3]=c[2];
    c[2]=c[1];
    c[1]=c[0];
    if (state >= 3) {
      c[0]=fn[0]/(pow(n[0],p[0])*pow(log(n[0]),q[0]));
    } else {
      c[0]=0;
    }
  }

  double PolyLog::power() const { return p[0]; }
  double PolyLog::logPower() const { return q[0]; }
  double PolyLog::constant() const { return c[0]; }

  bool PolyLog::valid() const { return state >= 3; }

  static double f(double n) { return 3*pow(n,2.5)*pow(log(n),0.5)+4*pow(n,1.5)+18; }

  void PolyLog::ok() {
    PolyLog fit;

    for (int k=2; k<=6; ++k) {
      double n = pow(10.0,k);
      fit.sample(n,f(n));
    }

    assert(fabs(fit.constant()-3.0)/3.0 < 1e-2);
    assert(fabs(fit.power()-2.5)/2.5 < 1e-2);
    assert(fabs(fit.logPower()-0.5)/0.5 < 1e-2);    

  }

}
