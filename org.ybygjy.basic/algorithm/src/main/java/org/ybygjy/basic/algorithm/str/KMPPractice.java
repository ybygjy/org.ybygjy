package org.ybygjy.basic.algorithm.str;

public class KMPPractice {
    public static int[] getNext(String b) {
        int len=b.length();
        int j=0;

        int next[]=new int[len+1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
        next[0]=next[1]=0;
        //i表示字符串的下标，从0开始
        for(int i=1;i<len;i++) {
            //j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
            while(j>0&&b.charAt(i)!=b.charAt(j)) {
                j=next[j];
            }
            if(b.charAt(i)==b.charAt(j)) {
                j++;
            }
            next[i+1]=j;
        }

        return next;
    }
    public void search(String src, String find, int[] next) {
        int j = 0;
        for (int i = 0; i < src.length(); i++) {
            while (j > 0 && src.charAt(i) != find.charAt(j)) {
                j = next[j];
            }
            if (src.charAt(i) == find.charAt(j)) {
                j ++;
            }
            if (j == find.length()) {
                System.out.println("find at position:" + (i - j));
                System.out.println(src.subSequence(i - j + 1, i + 1));
                j = next[j];
            }
        }
    }
    public static void main(String[] args) {
        String t = "AABFDABFDADFDSA";
        String p = "AADADFD";
        int[] rtnArr = KMPPractice.getNext(p);
        KMPPractice kmpPractice = new KMPPractice();
        kmpPractice.search(t, p, rtnArr);
    }
}
