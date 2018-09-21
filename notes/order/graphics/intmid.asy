import graph;
import math;

real xmin=0;
real xmax=10;

real ymin= 0;
real ymax= 10;

unitsize(6*2.54cm/(xmax-xmin));
defaultpen(fontsize(10pt));

int n=5;

real f(real x) { return sqrt(x+2); }

real eps =0.01;
void box(int k) {
  string label="$(" + string(k) + ",f(" + string(k) + "))$";
  dot((k,f(k)));
  label(label, (k,f(k+1)),NE);
  draw((k,f(k)) .. (k+0.25,f(k+1)),BeginArcArrow);
  draw((k+eps,0) -- (k+eps,f(k)) -- (k+1-eps,f(k)) -- (k+1-eps,0), black);
  draw((k,f(k)) .. (k+0.5,f(k+0.5)) .. (k+1,f(k+1)), red);
  draw((k,f(k-1)) .. (k+0.5,f(k-0.5)) .. (k+1,f(k)), blue);
  draw((k,f(k-0.5)) .. (k+0.5,f(k)) .. (k+1,f(k+0.5)), green);  
}

for (int k=0; k<n; ++k) {
  box(k);
}

write("ok");
