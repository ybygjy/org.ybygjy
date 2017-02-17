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
    public static void main(String[] args) {
        String t = "ABNCDFDADFDSA";
        String p = "FDADF";
        System.out.println(KMPPractice.strMatch(t, p));
    }
}
