package org.ybygjy.thrift.hello;

import java.util.Stack;

/**
 * String转义输出
 * @author WangYanCheng
 * @version 2016年9月14日
 */
public class StringUtils {
    public void doPrint(String s) {
        Stack<Character> dataStack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            dataStack.push(s.charAt(i));
        }
        StringBuilder sbud = new StringBuilder();
        while (!dataStack.isEmpty()) {
            Character tmpVal = dataStack.pop();
            if ('\\' != tmpVal) {
                sbud.append(tmpVal);
            } else {
                Character tmpVal2 = dataStack.pop();
                if (tmpVal2 == tmpVal) {
                    sbud.append('\\');
                } else {
                    sbud.append(tmpVal2);
                }
            }
        }
        System.out.println(sbud.toString());
    }
    public static void main(String[] args) {
        StringUtils strUtils = new StringUtils();
        strUtils.doPrint("\\a");
        strUtils.doPrint("\\\\a\\\\b\\c");
    }
}
