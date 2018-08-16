#!/usr/bin/env python3

import unittest
from prng import PRNG
from math import sqrt, fabs

class TestPRNG(unittest.TestCase):
    def setUp(self):
        self.INT32_MIN = -2**31
        self.INT32_MAX = 2**31 - 1

    def test_ok(self):
        png = PRNG(101)
        self.assertEqual(png.next(-9, 7), -8)
        
        self.assertEqual(png.next(self.INT32_MIN, self.INT32_MAX), -1716125422)
        
  
        self.assertEqual(png.next(-9,7), 3)
        self.assertEqual(png.next(self.INT32_MIN, self.INT32_MAX), 96111003)
    
        self.assertEqual(png.next(-9,7), -4);
        self.assertEqual(png.next(self.INT32_MIN, self.INT32_MAX), 1736830095)
        
        self.assertEqual(png.next(-9,7), -4)
        self.assertEqual(png.next(self.INT32_MIN, self.INT32_MAX), 1692926503)

    def test_bins(self):
        seeds = [0,1,-1,5,-5]
        for seed in seeds:
            for bins in range(2, 33):
                self.binsTest(seed,bins,100000)
    

    def binsTest(self, seed, numBins, numSamples):
        png = PRNG(seed)
        bins = [0]*numBins
        for i in range(numSamples):
            bins[png.next(0, numBins-1)] += 1

        p = 1/numBins
        mean = numSamples / numBins
        stdDev = sqrt(numSamples*(p*(1-p)))

        for i in range(numBins):
            z = (bins[i]-mean)/stdDev
            if fabs(z) >= 6:
                print("seed = {}".format(seed))        
                print("numBins = {}".format(numBins))
                print("numSamples = {}".format(numSamples))
                print("p = {}".format(p))
                print("mean = {}".format(mean))
                print("stdDev = {}".format(stdDev))
                print("x = {}".format(bins[i]))                                
                print("i = {}".format(i))
                print("z = {}".format(z))
                assert fabs(z) < 6


if __name__ == "__main__":
    unittest.main()
    print("testPRNG ok")