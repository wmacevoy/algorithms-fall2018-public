package csci480;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wmacevoy
 */
public class PRNG {

    private int[] state = new int[4];

    public PRNG(long seed) {
        init(seed);
    }

    public void init(long seed) {
        state[0] = (int) (seed);
        state[1] = (int) (seed >> 32);
        state[2] = (int) (~seed);
        state[3] = (int) (~seed >> 32);
        for (int i = 0; i < 8; ++i) {
            next();
        }
    }

    public int next() {
        int s, t = state[3];
        t ^= t << 11;
        t ^= (0x00FF_FFFF) & (t >> 8);
        state[3] = state[2];
        state[2] = state[1];
        state[1] = s = state[0];
        t ^= s;
        t ^= (0b0000_0000_0000_0000_0001_1111_1111_1111) & (s >> 19);
        state[0] = t;
        return t;
    }

    public int next(int a, int b) {
        if (b <= a) {
            return a;
        }
        long n = ((long) b) - ((long) a) + 1L;
        long x = (0x7FFF_FFFF_FFFF_FFFFL) & ((((long) next()) << 32) | ((0xFFFF_FFFFL) & (long) next()));
        return (int) (a + (x % n));
    }

}
