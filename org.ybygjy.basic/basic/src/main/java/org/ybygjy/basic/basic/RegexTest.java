package org.ybygjy.basic.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leye on 2017/8/3.
 */
public class RegexTest {
    public static void main(String[] args) {
        String src = "https://localhost/xyz/xactivity1111-retro_snaker/index.html?actId=##actId##&stationId=##stationId##&_dt_no_comment=false&_wvUseWKWebView=YES";
        Pattern pattern = Pattern.compile("(##([^#]+)##)");
        Matcher matcher = pattern.matcher(src);
        while(matcher.find()) {
            int groupCnt = matcher.groupCount();
            do {
                System.out.println(groupCnt + ":" + matcher.group(groupCnt--));
            } while (groupCnt >= 0);
        }
    }
}
