package org.ybygjy.basic.algorithm.lightcode;

/**
 * 比较字符串
 * <pre>
 *     比较两个字符串A和B，确定A中是否包含B中所有字符，字符串A和B中的字符都是大写字母
 * </pre>
 * Created by leye on 2017/1/25.
 */
public class CompareStrings {
    public boolean compareStrings(String A, String B) {
        //使用标记的办法
        int[] bitSets = new int[A.length()];
        //遍历字符串2
        //使用charAt
        //如果匹配则记录bitSets并跳到后序搜索
        for (int i = 0; i < B.length(); i++) {
            if (!this.innerIndexOf(bitSets, A, B.charAt(i), 0)) {
                return false;
            }
        }
        return true;
    }
    private boolean innerIndexOf(int[] bitSets, String A, char b, int threshold) {
        int index = A.indexOf(b, threshold);
        if (index < 0) {
            return false;
        }
        if (bitSets[index] == 1) {
            //递归
            return this.innerIndexOf(bitSets, A, b, (index + 1));
        }
        bitSets[index] = 1;
        return true;
    }
    public static void main(String[] args) {
        CompareStrings csInst = new CompareStrings();
        String tmpStr = "ABCDE:ABDEC,CCBAD:DABBC,CDEFA:AEDCF";
        for (String str : tmpStr.split(",")) {
            String[] innerStrs = str.split(":");
            boolean result = csInst.compareStrings(innerStrs[0], innerStrs[1]);
            System.out.println(innerStrs[0] + ":" + innerStrs[1] + "=>" + result);
        }
    }
}
