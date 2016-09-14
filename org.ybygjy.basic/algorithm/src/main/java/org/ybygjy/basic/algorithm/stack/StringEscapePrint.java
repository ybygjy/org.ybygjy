package org.ybygjy.basic.algorithm.stack;

import java.util.Stack;

/**
 * String转义输出
 * @author WangYanCheng
 * @version 2016年9月14日
 */
public class StringEscapePrint {
    /**
     * 给定字符串打印处理转义字符
     * @param s 字符串
     * @return rtnStr
     */
    public String escapePrint(String s) {
        Stack<Character> dataStack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            dataStack.push(s.charAt(i));
        }
        StringBuilder sbud = new StringBuilder();
        while (!dataStack.isEmpty()) {
            Character tmpVal = dataStack.pop();
            if ('\\' != tmpVal) {
                sbud.append(tmpVal);
            } else if(!dataStack.empty()) {
                Character tmpVal2 = dataStack.pop();
                if (tmpVal2 == tmpVal) {
                    sbud.append('\\');
                } else {
                    sbud.append(tmpVal2);
                }
            }
        }
        StringBuilder tmpStr = new StringBuilder();
        for (int i = sbud.length() - 1; i >= 0; i--) {
            tmpStr.append(sbud.charAt(i));
        }
        return tmpStr.toString();
    }
    /**
     * 字符串转义输出
     * @param s 字符串
     * @return rtnStr
     */
    public String escapeString(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sbud = new StringBuilder();
        for (int i = 0; i < chars.length;) {
            if (chars[i] == '\\') {
                if ((i+1 < chars.length)) {
                    sbud.append(chars[i+1]);
                    i += 2;
                } else {
                    i++;
                }
                continue;
            } else {
                sbud.append(chars[i]);
            }
            i++;
        }
        return sbud.toString();
    }
    /**
     * 测试入口
     * @param args 参数列表
     */
    public static void main(String[] args) {
        StringEscapePrint strUtils = new StringEscapePrint();
        String[] strArr = {"\\a\\a\\a\\a\\a\\a", "\\a\\b\\c", "\\a\\b\\c\\d\\\\e", "\\\\\\\\", "\\", "\\\\"};
        for (String tmpStr : strArr) {
            String rtnStr = strUtils.escapePrint(tmpStr);
            String rtnStr2 = strUtils.escapeString(tmpStr);
            System.out.println(tmpStr + "->" + rtnStr + "->" + rtnStr2);
        }
    }
}
