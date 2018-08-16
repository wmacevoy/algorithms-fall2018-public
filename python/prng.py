#! /usr/bin/env python3
from ctypes import c_uint as uint32

class PRNG:
    def __init__(self, seed=1):
        self.state = [0]*4
        self.state[0] = seed
        self.state[1] = seed >> 32
        self.state[2] = ~seed
        self.state[3] = ~seed >> 32
        for i in range(8):
            self.__next()

    def __next(self):
        t = uint32(self.state[3])
        t = uint32(t.value ^ t.value << 11)
        t = uint32(t.value ^ t.value >> 8)
        self.state[3] = self.state[2]
        self.state[2] = self.state[1]
        self.state[1] = self.state[0]
        s = uint32(self.state[0])
        t = uint32(t.value ^ s.value)
        t = uint32(t.value ^ s.value >> 19)
        self.state[0] = t.value
        return t.value

    def next(self, a, b):
        if b <= a: return a
        n = b - a + 1
        x = 0x7FFFFFFFFFFFFFFF & ((self.__next() << 32) | self.__next())
        return a + (x % n)


