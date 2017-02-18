package org.ybygjy.basic.algorithm.str;

/**
 * 实践KMP算法
 * Created by leye on 2017/2/17.
 */
public class KMPPractice {
    class KMPAlgorithm {
        /**
         * Brute-Force
         * @param str str
         * @param sub sub
         * @return rtnFlag
         */
        public int bf(String str, String sub) {
            char cstr[] = str.toCharArray();
            char csub[] = sub.toCharArray();
            int i = 0;//Main string's index
            int j = 0;//Sub string's index
            while (j < cstr.length && j < csub.length) {
                if (cstr[i] == csub[j]) {
                    i++;
                    j++;
                } else {
                    //if not match, the main string's index must be come back
                    j = 0;
                    i = i - j + 1;
                }
            }
            //if successful
            if (j == csub.length) {
                //return the first character's index
                return i - j;
            } else {
                return -1;
            }
        }

        /**
         * next[j] == k; how to get the next array is the most important thing in the KMP
         * @param sub sub
         * @return rtnIntArr
         */
        public int[] next(String sub) {
            int[] next = new int[sub.length()];
            char[] c = sub.toCharArray();
            int j = 0;
            int k = -1;
            while (j < c.length - 1) {
                if (k == -1 || c[j] == c[k]) {
                    next[j] = k;
                    j++;
                    k++;
                } else {
                    k = next[k];
                }
            }
            return next;
        }
        public int kmp(String str, String sub) {
            char[] c1 = str.toCharArray();
            char[] c2 = sub.toCharArray();
            int i = 0;
            int j = 0;
            int[] next = next(sub);
            while (i < c1.length && j < c2.length) {
                if (j == -1 || c1[i] == c2[j]) {
                    i++;
                    j++;
                } else {
                    //i need not to come back
                    j = next[j];
                }
            }
            //if successful
            if (j == c2.length) {
                //return the first character's index
                return  i - j;
            } else {
                return -1;
            }
        }
    }

    public static int kmpPower(String str, String sub) {
        return new KMPPractice().new KMPAlgorithm().kmp(str, sub);
    }
    public static void main(String[] args) {
        String t = "ABNCDFDADFDSA";
        String p = "FDADF";
        System.out.println(KMPPractice.kmpPower(t, p));
    }
}
