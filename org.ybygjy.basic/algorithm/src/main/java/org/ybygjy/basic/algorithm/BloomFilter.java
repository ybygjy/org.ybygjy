package org.ybygjy.basic.algorithm;

import java.util.BitSet;

/**
 * BloomFilter 多hash函数映射的快速查找算法
 * Created by leye on 2017/1/23.
 */
public class BloomFilter {
    /** BitSet初始分配2^24个Bit*/
    private static final int DEFAULT_SIZE = 1 << 25;
    /** 不同Hash函数的种子，一般应取素数*/
    private static final int[] seeds = new int[] {5, 7, 11, 13, 31, 37, 61};
    private BitSet bitSet = new BitSet(DEFAULT_SIZE);
    /** Hash函数对象*/
    private SimpleHash[] simpleHashes = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            simpleHashes[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    /**
     * 将字符串标记到Bits中
     * @param value
     */
    public void add(String value) {
        for (SimpleHash simpleHash : simpleHashes) {
            bitSet.set(simpleHash.hash(value), true);
        }
    }

    /**
     * 判断字符串是否已经被Bits标记
     * @param value value
     * @return rtnFlag true/false
     */
    public boolean contains(String value) {
        if (null == value) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash simpleHash : simpleHashes) {
            ret = ret && bitSet.get(simpleHash.hash(value));
        }
        return ret;
    }

    static class SimpleHash {
        private int cap;
        private int seed;
        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        public int hash(String val) {
            int result = 0;
            int len = val.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + val.charAt(i);
            }
            return (cap - 1) & result;
        }
    }

    /**
     * 测试入口
     * @param args args
     */
    public static void main(String[] args) {
        String[] urls = new String[]{"http://url001", "http://url002", "http://url003", "http://url000", "http://url001"};
        BloomFilter bloomFilter = new BloomFilter();
        for (String url : urls) {
            bloomFilter.add(url);
        }
        for (String url : urls) {
            System.out.println(url + "=>" + bloomFilter.contains(url));
        }
    }
}
