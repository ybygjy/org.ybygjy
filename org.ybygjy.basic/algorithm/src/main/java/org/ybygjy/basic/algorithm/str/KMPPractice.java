package org.ybygjy.basic.algorithm.str;

/**
 * 实践KMP算法
 * Created by leye on 2017/2/17.
 */
public class KMPPractice {
    /**
     * 穷举法
     * @param t t
     * @param p p
     * @return rtnInt -1/rtn
     */
    public static int strMatch(String t, String p) {
        for (int i = 0; i < t.length(); i++) {
            if (i + p.length() > t.length()) {
                break;
            }
            boolean match = true;
            for (int j = 0; j < p.length(); j++) {
                if (t.charAt(i + j) != p.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }
    class KMPArithmetic {
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
         * next[j] == k;
         * @param sub
         * @return
         */
        public int[] next(String sub) {

        }
    }
    public static void main(String[] args) {
        String t = "ABNCDFDADFDSA";
        String p = "FDADF";
        System.out.println(KMPPractice.strMatch(t, p));
    }
}
