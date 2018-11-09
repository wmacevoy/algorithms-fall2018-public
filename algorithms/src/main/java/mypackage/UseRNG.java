/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.util.Random;
import csci480.PRNG;



/**
 *
 * @author wmacevoy
 */

class Node {
    Node(String value) {
        this.value = value;
    }
    Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right=right;
    }
    String value;
    Node left,right;
    @Override
    public String toString() {
        return value + "(" + left + "," + right + ")";
    }
}

public class UseRNG {
    PRNG rng = new PRNG();
    
    String mkRandomString(int minSize, int maxSize) {
        StringBuilder sb = new StringBuilder();
        int size = rng.nextInt(minSize,maxSize);
        for (int i=0; i<size; ++i) {
            sb.append((char) rng.nextInt('a','z'));
        }
        return sb.toString();
    }
    
    Node mkRandomTree(int size) {
        if (size == 0) {
            return null;
        }
        --size;
        int sizeLeft = rng.nextInt(0,size);
        int sizeRight = size-sizeLeft;
        return new Node(mkRandomString(2,5),mkRandomTree(sizeLeft), mkRandomTree(sizeRight));
    }

    public static void main(String [] args) {
        new UseRNG().run();
    }
    
    void run() {
//        rng.setSeed(3);
        System.out.println("tree=" + mkRandomTree(5));
    }
    
    private Random PRNG() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
