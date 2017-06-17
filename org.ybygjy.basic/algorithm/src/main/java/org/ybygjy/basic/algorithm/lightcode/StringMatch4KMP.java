package org.ybygjy.basic.algorithm.lightcode;

/**
 * Created by leye on 2017/2/6.
 */
public class StringMatch4KMP {
    public static int doMatch(String source, String target) {
        //1.定义变量表示源字符串和目标字符串的下标
        //2.目标字符串长度小于源字符串长度
        //3.源字符串长度减源字符串匹配下标不能小于目标字符串长度
        int sourceIndex = 0, targetIndex = 0;
        return -1;
    }
    public static void main(String[] args) {
        String source = "ABCDEF AABDCDEFA ABCDEFAD";
        String target = "BDCDEFA";
        int result = StringMatch4KMP.doMatch(source, target);
        System.out.println(result);
    }
}
