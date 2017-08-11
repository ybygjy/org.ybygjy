package org.ybygjy.basic.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leye on 2017/8/3.
 */
public class RegexTest {
    public static void main(String[] args) {
        String src = "四川省^^^成都市^^^金牛区^^^驷马桥街道^^^普兰县233号^(测试站不要选择）";
        Pattern pattern = Pattern.compile("^.*\\^{3}(.*)$");
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
